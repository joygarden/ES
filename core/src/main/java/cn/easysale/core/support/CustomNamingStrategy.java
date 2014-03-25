package cn.easysale.core.support;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.cfg.NamingStrategy;
import org.springframework.stereotype.Component;

/**
 * Created by liaozhisong on 3/22/14.
 */
@Component
public class CustomNamingStrategy extends ImprovedNamingStrategy implements NamingStrategy {
    @Override
    public String columnName(String columnName) {
        return addUnderscores(columnName).toLowerCase();
    }

    @Override
    public String tableName(String tableName) {
        return addUnderscores(tableName).toLowerCase();
    }

    @Override
    public String propertyToColumnName(String propertyName) {
        return addUnderscores(propertyName).toLowerCase();
    }

    @Override
    public String classToTableName(String className) {
        return "t_" + tableName(className);
    }
}
