package cn.easysale.core.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liaozhisong on 3/22/14.
 */
public abstract class BaseService<T extends BaseEntity> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @PersistenceContext
    private EntityManager em;

    //－－－ 绑定当前实体快捷方法 begin－－－－
    public T findById(Serializable id) {
        return em.find(entityClass, id);
    }

    public void deleteObjectById(Serializable id) {
        T t = findById(id);
        em.remove(t);
    }

    public List<T> findBy(String propertyName, Object value) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(propertyName, value);
        String jql = "from " + entityClass.getSimpleName() + " where " + propertyName + "=:" + propertyName;
        return findByJql(jql, params);
    }

    public T findUniqueBy(String propertyName, Object value) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(propertyName, value);
        String jql = "from " + entityClass.getSimpleName() + " where " + propertyName + "=:" + propertyName;
        return (T) buildQuery(jql, params).getSingleResult();
    }

    public List<T> findAll() {
        String jql = "from " + entityClass.getName();
        return findByJql(jql);
    }
    //－－－ 绑定当前实体快捷方法 end－－－－

    public <A> A find(Class<A> clazz, Serializable id) {
        return em.find(clazz, id);
    }

    public <A extends BaseEntity> void addObject(A a) {
        a.setCreateDate(new Date());
        em.persist(a);
    }

    public <A extends BaseEntity> void updateObject(A a) {
        a.setUpdateDate(new Date());
        em.merge(a);
    }

    public <A> void deleteObject(A a) {
        em.remove(a);
    }

    /**
     * 列表查询 用法:
     * String jql = "from User where age=:age and name like :name and id in(:ids);
     * params.put("age",age);
     * params.put("name","%"+name+"%");
     * params.put("ids",Arrays.asList(new Object[]{3l,4l,13l}));
     * findByjql(jql,params)
     *
     * @param jql    jql语句
     * @param params 参数
     * @return List集合
     */
    public List findByJql(String jql, Map<String, Object> params) {
        return buildQuery(jql, params).getResultList();
    }

    /**
     * 列表查询
     *
     * @param jql jql语句
     * @return List集合
     */
    public List findByJql(String jql) {
        return findByJql(jql, null);
    }

    /**
     * 分页查询
     *
     * @param jql    jql语句
     * @param page   分页参数 需设置pageNo,pageSize
     * @param params 参数
     * @return 分页对象
     */
    public Page pagedQuery(String jql, Page page, Map<String, Object> params) {
        if (page == null) page = new Page();
        String countjql = "select count(*) " + removeSelect(removeOrders(jql));
        page.setTotal((Long) buildQuery(countjql, params).getSingleResult());
        List list = buildQuery(jql, params)
                .setFirstResult((page.getPageNo() - 1) * page.getPageSize())
                .setMaxResults(page.getPageSize()).getResultList();
        page.setData(list);
        return page;
    }

    /**
     * 分页查询
     *
     * @param jql  jql语句
     * @param page 分页参数 需设置pageNo,pageSize
     * @return 分页对象
     */
    public Page pagedQuery(String jql, Page page) {
        return pagedQuery(jql, page, null);
    }

    private Query buildQuery(String jql, Map<String, Object> params) {
        Query query = em.createQuery(jql);
        if (params != null) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        return query;
    }

    private static String removeSelect(String jql) {
        int beginPos = jql.toLowerCase().indexOf("from");
        return jql.substring(beginPos);
    }

    private static String removeOrders(String jql) {
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(jql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }


}
