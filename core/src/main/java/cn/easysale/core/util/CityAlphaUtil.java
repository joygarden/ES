package cn.easysale.core.util;


import cn.easysale.core.entity.VisitorTrace;
import cn.easysale.core.util.geo.IP138Locator;
import cn.easysale.core.util.geo.IPLocator;
import cn.easysale.core.util.geo.geoip.Location;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: cwhl2201
 * Date: 13-5-28
 * Time: 上午2:06
 * To change this template use File | Settings | File Templates.
 */
public class CityAlphaUtil {


    private static Map<String, String> cityAlpha = new HashMap<String, String>();
    public static Set<String> provences = new HashSet<String>();
    public static Set<String> provenceCity = new HashSet<String>();

    public static void findCity(String visitIp, VisitorTrace ut) {

        String[] info = IP138Locator.parserIp(visitIp);

        if("北京市".equals(info[0])||"北京市区".equals(info[0])||"上海市".equals(info[0])||"天津市".equals(info[0])||"重庆市".equals(info[0]))
        {
           ut.setProvince(info[0]);
        }

        ut.setArea(info[1] == null ? "" : info[1]);
        ut.setProvince(info[0] == null ? "" : info[0]);

        if (ut.getArea()==null||ut.getArea().equals("")) {
            Location location = IPLocator.getLoaction(visitIp);

            if(location == null ||  location.city == null||location.city.equals("")){
                 ut.setArea("");
            }
            else if (location.city.equalsIgnoreCase("Hebei")||location.city.equalsIgnoreCase("GUIZHOU")) {
                ut.setArea(CityAlphaUtil.getCityName(location.city).trim());
            }else if(CityAlphaUtil.getCityName(location.city)!=null){
                 ut.setArea(CityAlphaUtil.getCityName(location.city).trim()+"市");
            }else
             {
                ut.setArea("");
            }
        }
    }

    static {
         provenceCity.add("北京市");
        provenceCity.add("上海市");
        provenceCity.add("天津市");
        provenceCity.add("重庆市");
        provenceCity.add("香港特别行政区");
        provenceCity.add("澳门特别行政区");


        provences.add("山东省");
        provences.add("黑龙江省");
        provences.add("吉林省");
        provences.add("辽宁省");
        provences.add("内蒙古自治区");
        provences.add("河北省");
        provences.add("山西省");
        provences.add("陕西省");
        provences.add("江苏省");
        provences.add("浙江省");
        provences.add("甘肃省");
        provences.add("河南省");
        provences.add("青海省");
        provences.add("安徽省");
        provences.add("宁夏回族自治区");
        provences.add("湖北省");
        provences.add("湖南省");
        provences.add("江西省");
        provences.add("福建省");
        provences.add("广东省");
        provences.add("广西壮族自治区");
        provences.add("贵州省");
        provences.add("云南省");
        provences.add("四川省");
        provences.add("安徽省");
        provences.add("贵州省");
        provences.add("福建省");
        provences.add("台湾省");
        provences.add("海南省");
        provences.add("西藏自治区");
        provences.add("新疆维吾尔自治区");




        cityAlpha.put("BEIJING", "北京");
        cityAlpha.put("SHANGHAI", "上海");
        cityAlpha.put("TIANJIN", "天津");
        cityAlpha.put("TIANJIN", "天津");
        cityAlpha.put("CHONGQING", "重庆");
        cityAlpha.put("QINGDAO", "青岛");
        cityAlpha.put("JINAN", "济南");
        cityAlpha.put("GUANGZHOU", "广州");
        cityAlpha.put("HANGZHOU", "杭州");
        cityAlpha.put("NANJING", "南京");
        cityAlpha.put("TAIAN", "泰安");
        cityAlpha.put("WENZHOU", "温州");
        cityAlpha.put("HEBEI", "河北省");
        cityAlpha.put("SANYA", "三亚");
        cityAlpha.put("HAIKOU", "海口");
        cityAlpha.put("XIAN", "西安");
        cityAlpha.put("RUIAN", "瑞安");
        cityAlpha.put("KUNSHAN", "昆山");
        cityAlpha.put("LANGFANG", "廊坊");
        cityAlpha.put("ZIBO", "淄博");
        cityAlpha.put("BEIJING", "北京             ");
        cityAlpha.put("SHANGHAI", "上海            ");
        cityAlpha.put("TIANJIN", "天津             ");
        cityAlpha.put("CHONGQING", "重庆           ");
        cityAlpha.put("AKESU", "阿克苏             ");
        cityAlpha.put("ANNING", "安宁              ");
        cityAlpha.put("ANQING", "安庆              ");
        cityAlpha.put("ANSHAN", "鞍山              ");
        cityAlpha.put("ANSHUN", "安顺              ");
        cityAlpha.put("ANYANG", "安阳              ");
        cityAlpha.put("BAICHENG", "白城            ");
        cityAlpha.put("BAISHAN", "白山             ");
        cityAlpha.put("BAIYIN", "白银              ");
        cityAlpha.put("BENGBU", "蚌埠              ");
        cityAlpha.put("BAODING", "保定             ");
        cityAlpha.put("BAOJI", "宝鸡               ");
        cityAlpha.put("BAOSHAN", "保山             ");
        cityAlpha.put("BAZHONG", "巴中             ");
        cityAlpha.put("BEIHAI", "北海              ");
        cityAlpha.put("BENXI", "本溪               ");
        cityAlpha.put("BINZHOU", "滨州             ");
        cityAlpha.put("BOLE", "博乐                ");
        cityAlpha.put("BOZHOU", "亳州              ");
        cityAlpha.put("CANGZHOU", "沧州            ");
        cityAlpha.put("CHANGDE", "常德             ");
        cityAlpha.put("CHANGJI", "昌吉             ");
        cityAlpha.put("CHANGSHU", "常熟            ");
        cityAlpha.put("CHANGZHOU", "常州           ");
        cityAlpha.put("CHAOHU", "巢湖              ");
        cityAlpha.put("CHAOYANG", "朝阳            ");
        cityAlpha.put("CHAOZHOU", "潮州            ");
        cityAlpha.put("CHENGDE", "承德             ");
        cityAlpha.put("CHENGDU", "成都             ");
        cityAlpha.put("CHENGGU", "城固             ");
        cityAlpha.put("CHENZHOU", "郴州            ");
        cityAlpha.put("CHIBI", "赤壁               ");
        cityAlpha.put("CHIFENG", "赤峰             ");
        cityAlpha.put("CHISHUI", "赤水             ");
        cityAlpha.put("CHIZHOU", "池州             ");
        cityAlpha.put("CHONGZUO", "崇左            ");
        cityAlpha.put("CHUXIONG", "楚雄            ");
        cityAlpha.put("CHUZHOU", "滁州             ");
        cityAlpha.put("CIXI", "慈溪                ");
        cityAlpha.put("CONGHUA", "从化             ");
        cityAlpha.put("DALI", "大理                ");
        cityAlpha.put("DALIAN", "大连              ");
        cityAlpha.put("DANDONG", "丹东             ");
        cityAlpha.put("DANYANG", "丹阳             ");
        cityAlpha.put("DAQING", "大庆              ");
        cityAlpha.put("DATONG", "大同              ");
        cityAlpha.put("DAZHOU", "达州              ");
        cityAlpha.put("DEYANG", "德阳              ");
        cityAlpha.put("DEZHOU", "德州              ");
        cityAlpha.put("DONGGUAN", "东莞            ");
        cityAlpha.put("DONGYANG", "东阳            ");
        cityAlpha.put("DONGYING", "东营            ");
        cityAlpha.put("DOUYUN", "都匀              ");
        cityAlpha.put("DUNHUA", "敦化              ");
        cityAlpha.put("EERDUOSI", "鄂尔多斯        ");
        cityAlpha.put("ENSHI", "恩施               ");
        cityAlpha.put("FANGCHENGGANG", "防城港     ");
        cityAlpha.put("FEICHENG", "肥城            ");
        cityAlpha.put("FENGHUA", "奉化             ");
        cityAlpha.put("FUSHUN", "抚顺              ");
        cityAlpha.put("FUXIN", "阜新               ");
        cityAlpha.put("FUYANG", "阜阳              ");
        cityAlpha.put("FUYANG1", "富阳             ");
        cityAlpha.put("FUZHOU", "福州              ");
        cityAlpha.put("FUZHOU1", "抚州             ");
        cityAlpha.put("GANYU", "赣榆               ");
        cityAlpha.put("GANZHOU", "赣州             ");
        cityAlpha.put("GAOMING", "高明             ");
        cityAlpha.put("GAOYOU", "高邮              ");
        cityAlpha.put("GEERMU", "格尔木            ");
        cityAlpha.put("GEJIU", "个旧               ");
        cityAlpha.put("GONGYI", "巩义              ");
        cityAlpha.put("GUANGAN", "广安             ");
        cityAlpha.put("GUANGYUAN", "广元           ");
        cityAlpha.put("GUANGZHOU", "广州           ");
        cityAlpha.put("GUBAOTOU", "古包头          ");
        cityAlpha.put("GUIGANG", "贵港             ");
        cityAlpha.put("GUILIN", "桂林              ");
        cityAlpha.put("GUIYANG", "贵阳             ");
        cityAlpha.put("GUYUAN", "固原              ");
        cityAlpha.put("HAERBIN", "哈尔滨           ");
        cityAlpha.put("HAICHENG", "海城            ");
        cityAlpha.put("HAIKOU", "海口              ");
        cityAlpha.put("HAIMEN", "海门              ");
        cityAlpha.put("HAINING", "海宁             ");
        cityAlpha.put("HAMI", "哈密                ");
        cityAlpha.put("HANDAN", "邯郸              ");
        cityAlpha.put("HANGZHOU", "杭州            ");
        cityAlpha.put("HANZHONG", "汉中            ");
        cityAlpha.put("HEBI", "鹤壁                ");
        cityAlpha.put("HEFEI", "合肥               ");
        cityAlpha.put("HENGSHUI", "衡水            ");
        cityAlpha.put("HENGYANG", "衡阳            ");
        cityAlpha.put("HETIAN", "和田              ");
        cityAlpha.put("HEYUAN", "河源              ");
        cityAlpha.put("HEZE", "菏泽                ");
        cityAlpha.put("HUADOU", "花都              ");
        cityAlpha.put("HUAIAN", "淮安              ");
        cityAlpha.put("HUAIBEI", "淮北             ");
        cityAlpha.put("HUAIHUA", "怀化             ");
        cityAlpha.put("HUAINAN", "淮南             ");
        cityAlpha.put("HUANGGANG", "黄冈           ");
        cityAlpha.put("HUANGSHAN", "黄山           ");
        cityAlpha.put("HUANGSHI", "黄石            ");
        cityAlpha.put("HUHEHAOTE", "呼和浩特       ");
        cityAlpha.put("HUIZHOU", "惠州             ");
        cityAlpha.put("HULUDAO", "葫芦岛           ");
        cityAlpha.put("HUZHOU", "湖州              ");
        cityAlpha.put("JIAMUSI", "佳木斯           ");
        cityAlpha.put("JIAN", "吉安                ");
        cityAlpha.put("JIANGDOU", "江都            ");
        cityAlpha.put("JIANGMEN", "江门            ");
        cityAlpha.put("JIANGYIN", "江阴            ");
        cityAlpha.put("JIAONAN", "胶南             ");
        cityAlpha.put("JIAOZHOU", "胶州            ");
        cityAlpha.put("JIAOZUO", "焦作             ");
        cityAlpha.put("JIASHAN", "嘉善             ");
        cityAlpha.put("JIAXING", "嘉兴             ");
        cityAlpha.put("JIEXIU", "介休              ");
        cityAlpha.put("JILIN", "吉林               ");
        cityAlpha.put("JIMO", "即墨                ");
        cityAlpha.put("JINAN", "济南               ");
        cityAlpha.put("JINCHENG", "晋城            ");
        cityAlpha.put("JINGDEZHEN", "景德镇        ");
        cityAlpha.put("JINGHONG", "景洪            ");
        cityAlpha.put("JINGJIANG", "靖江           ");
        cityAlpha.put("JINGMEN", "荆门             ");
        cityAlpha.put("JINGZHOU", "荆州            ");
        cityAlpha.put("JINHUA", "金华              ");
        cityAlpha.put("JINING1", "集宁             ");
        cityAlpha.put("JINING", "济宁              ");
        cityAlpha.put("JINJIANG", "晋江            ");
        cityAlpha.put("JINTAN", "金坛              ");
        cityAlpha.put("JINZHONG", "晋中            ");
        cityAlpha.put("JINZHOU", "锦州             ");
        cityAlpha.put("JISHOU", "吉首              ");
        cityAlpha.put("JIUJIANG", "九江            ");
        cityAlpha.put("JIUQUAN", "酒泉             ");
        cityAlpha.put("JIXI", "鸡西                ");
        cityAlpha.put("JIYUAN", "济源              ");
        cityAlpha.put("JURONG", "句容              ");
        cityAlpha.put("KAIFENG", "开封             ");
        cityAlpha.put("KAILI", "凯里               ");
        cityAlpha.put("KAIPING", "开平             ");
        cityAlpha.put("KAIYUAN", "开远             ");
        cityAlpha.put("KASHEN", "喀什              ");
        cityAlpha.put("KELAMAYI", "克拉玛依        ");
        cityAlpha.put("KUERLE", "库尔勒            ");
        cityAlpha.put("KUITUN", "奎屯              ");
        cityAlpha.put("KUNMING", "昆明             ");
        cityAlpha.put("KUNSHAN", "昆山             ");
        cityAlpha.put("LAIBIN", "来宾              ");
        cityAlpha.put("LAIWU", "莱芜               ");
        cityAlpha.put("LAIXI", "莱西               ");
        cityAlpha.put("LAIZHOU", "莱州             ");
        cityAlpha.put("LANGFANG", "廊坊            ");
        cityAlpha.put("LANZHOU", "兰州             ");
        cityAlpha.put("LASA", "拉萨                ");
        cityAlpha.put("LESHAN", "乐山              ");
        cityAlpha.put("LIANYUNGANG", "连云港       ");
        cityAlpha.put("LIAOCHENG", "聊城           ");
        cityAlpha.put("LIAOYANG", "辽阳            ");
        cityAlpha.put("LIAOYUAN", "辽源            ");
        cityAlpha.put("LIJIANG", "丽江             ");
        cityAlpha.put("LINAN", "临安               ");
        cityAlpha.put("LINCANG", "临沧             ");
        cityAlpha.put("LINFEN", "临汾              ");
        cityAlpha.put("LINGBAO", "灵宝             ");
        cityAlpha.put("LINHE", "临河               ");
        cityAlpha.put("LINXIA", "临夏              ");
        cityAlpha.put("LINYI", "临沂               ");
        cityAlpha.put("LISHUI", "丽水              ");
        cityAlpha.put("LIUAN", "六安               ");
        cityAlpha.put("LIUPANSHUI", "六盘水        ");
        cityAlpha.put("LIUZHOU", "柳州             ");
        cityAlpha.put("LIYANG", "溧阳              ");
        cityAlpha.put("LONGHAI", "龙海             ");
        cityAlpha.put("LONGYAN", "龙岩             ");
        cityAlpha.put("LOUDI", "娄底               ");
        cityAlpha.put("LUOHE", "漯河               ");
        cityAlpha.put("LUOYANG", "洛阳             ");
        cityAlpha.put("LUXI", "潞西                ");
        cityAlpha.put("LUZHOU", "泸州              ");
        cityAlpha.put("LVLIANG", "吕梁             ");
        cityAlpha.put("LVSHUN", "旅顺              ");
        cityAlpha.put("MAANSHAN", "马鞍山          ");
        cityAlpha.put("MAOMING", "茂名             ");
        cityAlpha.put("MEIHEKOU", "梅河口          ");
        cityAlpha.put("MEISHAN", "眉山             ");
        cityAlpha.put("MEIZHOU", "梅州             ");
        cityAlpha.put("MIANXIAN", "勉县            ");
        cityAlpha.put("MIANYANG", "绵阳            ");
        cityAlpha.put("MUDANJIANG", "牡丹江        ");
        cityAlpha.put("NANAN", "南安               ");
        cityAlpha.put("NANCHANG", "南昌            ");
        cityAlpha.put("NANCHONG", "南充            ");
        cityAlpha.put("NANJING", "南京             ");
        cityAlpha.put("NANNING", "南宁             ");
        cityAlpha.put("NANPING", "南平             ");
        cityAlpha.put("NANTONG", "南通             ");
        cityAlpha.put("NANYANG", "南阳             ");
        cityAlpha.put("NEIJIANG", "内江            ");
        cityAlpha.put("NINGBO", "宁波              ");
        cityAlpha.put("NINGDE", "宁德              ");
        cityAlpha.put("PANJIN", "盘锦              ");
        cityAlpha.put("PANZHIHUA", "攀枝花         ");
        cityAlpha.put("PENGLAI", "蓬莱             ");
        cityAlpha.put("PINGDINGSHAN", "平顶山      ");
        cityAlpha.put("PINGDU", "平度              ");
        cityAlpha.put("PINGHU", "平湖              ");
        cityAlpha.put("PINGLIANG", "平凉           ");
        cityAlpha.put("PINGXIANG", "萍乡           ");
        cityAlpha.put("PULANDIAN", "普兰店         ");
        cityAlpha.put("PUNING", "普宁              ");
        cityAlpha.put("PUTIAN", "莆田              ");
        cityAlpha.put("PUYANG", "濮阳              ");
        cityAlpha.put("QIANNAN", "黔南             ");
        cityAlpha.put("QIDONG", "启东              ");
        cityAlpha.put("QINGDAO", "青岛             ");
        cityAlpha.put("QINGYANG", "庆阳            ");
        cityAlpha.put("QINGYUAN", "清远            ");
        cityAlpha.put("QINGZHOU", "青州            ");
        cityAlpha.put("QINHUANGDAO", "秦皇岛       ");
        cityAlpha.put("QINZHOU", "钦州             ");
        cityAlpha.put("QIONGHAI", "琼海            ");
        cityAlpha.put("QIQIHAER", "齐齐哈尔        ");
        cityAlpha.put("QUANZHOU", "泉州            ");
        cityAlpha.put("QUJING", "曲靖              ");
        cityAlpha.put("QUZHOU", "衢州              ");
        cityAlpha.put("RIKAZE", "日喀则            ");
        cityAlpha.put("RIZHAO", "日照              ");
        cityAlpha.put("RONGCHENG", "荣成           ");
        cityAlpha.put("RUGAO", "如皋               ");
        cityAlpha.put("RUIAN", "瑞安               ");
        cityAlpha.put("RUSHAN", "乳山              ");
        cityAlpha.put("SANMENXIA", "三门峡         ");
        cityAlpha.put("SANMING", "三明             ");
        cityAlpha.put("SANYA", "三亚               ");
        cityAlpha.put("XIAMEN", "厦门              ");
        cityAlpha.put("SHAN", "佛山                ");
        cityAlpha.put("SHANGLUO", "商洛            ");
        cityAlpha.put("SHANGQIU", "商丘            ");
        cityAlpha.put("SHANGRAO", "上饶            ");
        cityAlpha.put("SHANGYU", "上虞             ");
        cityAlpha.put("SHANTOU", "汕头             ");
        cityAlpha.put("ANKANG", "安康              ");
        cityAlpha.put("SHAOGUAN", "韶关            ");
        cityAlpha.put("SHAOXING", "绍兴            ");
        cityAlpha.put("SHAOYANG", "邵阳            ");
        cityAlpha.put("SHENYANG", "沈阳            ");
        cityAlpha.put("SHENZHEN", "深圳            ");
        cityAlpha.put("SHIHEZI", "石河子           ");
        cityAlpha.put("SHIJIAZHUANG", "石家庄      ");
        cityAlpha.put("SHILIN", "石林              ");
        cityAlpha.put("SHISHI", "石狮              ");
        cityAlpha.put("SHIYAN", "十堰              ");
        cityAlpha.put("SHOUGUANG", "寿光           ");
        cityAlpha.put("SHUANGYASHAN", "双鸭山      ");
        cityAlpha.put("SHUOZHOU", "朔州            ");
        cityAlpha.put("SHUYANG", "沭阳             ");
        cityAlpha.put("SIMAO", "思茅               ");
        cityAlpha.put("SIPING", "四平              ");
        cityAlpha.put("SONGYUAN", "松原            ");
        cityAlpha.put("SUINING", "遂宁             ");
        cityAlpha.put("SUIZHOU", "随州             ");
        cityAlpha.put("SUZHOU", "苏州              ");
        cityAlpha.put("TACHENG", "塔城             ");
        cityAlpha.put("TAIAN", "泰安               ");
        cityAlpha.put("TAICANG", "太仓             ");
        cityAlpha.put("TAIXING", "泰兴             ");
        cityAlpha.put("TAIYUAN", "太原             ");
        cityAlpha.put("TAIZHOU", "泰州             ");
        cityAlpha.put("TAIZHOU1", "台州            ");
        cityAlpha.put("TANGSHAN", "唐山            ");
        cityAlpha.put("TENGCHONG", "腾冲           ");
        cityAlpha.put("TENGZHOU", "滕州            ");
        cityAlpha.put("TIANMEN", "天门             ");
        cityAlpha.put("TIANSHUI", "天水            ");
        cityAlpha.put("TIELING", "铁岭             ");
        cityAlpha.put("TONGCHUAN", "铜川           ");
        cityAlpha.put("TONGLIAO", "通辽            ");
        cityAlpha.put("TONGLING", "铜陵            ");
        cityAlpha.put("TONGLU", "桐庐              ");
        cityAlpha.put("TONGREN", "铜仁             ");
        cityAlpha.put("TONGXIANG", "桐乡           ");
        cityAlpha.put("TONGZHOU", "通州            ");
        cityAlpha.put("TONGHUA", "通化             ");
        cityAlpha.put("TULUFAN", "吐鲁番           ");
        cityAlpha.put("WAFANGDIAN", "瓦房店        ");
        cityAlpha.put("WEIFANG", "潍坊             ");
        cityAlpha.put("WEIHAI", "威海              ");
        cityAlpha.put("WEINAN", "渭南              ");
        cityAlpha.put("WENDENG", "文登             ");
        cityAlpha.put("WENLING", "温岭             ");
        cityAlpha.put("WENZHOU", "温州             ");
        cityAlpha.put("WUHAI", "乌海               ");
        cityAlpha.put("WUHAN", "武汉               ");
        cityAlpha.put("WUHU", "芜湖                ");
        cityAlpha.put("WUJIANG", "吴江             ");
        cityAlpha.put("WULANHAOTE", "乌兰浩特      ");
        cityAlpha.put("WUWEI", "武威               ");
        cityAlpha.put("WUXI", "无锡                ");
        cityAlpha.put("WUZHOU", "梧州              ");
        cityAlpha.put("XIAN", "西安                ");
        cityAlpha.put("XIANGCHENG", "项城          ");
        cityAlpha.put("XIANGFAN", "襄樊            ");
        cityAlpha.put("XIANGGELILA", "香格里拉     ");
        cityAlpha.put("XIANGSHAN", "象山           ");
        cityAlpha.put("XIANGTAN", "湘潭            ");
        cityAlpha.put("XIANGXIANG", "湘乡          ");
        cityAlpha.put("XIANNING", "咸宁            ");
        cityAlpha.put("XIANTAO", "仙桃             ");
        cityAlpha.put("XIANYANG", "咸阳            ");
        cityAlpha.put("XICANG", "西藏              ");
        cityAlpha.put("XICHANG", "西昌             ");
        cityAlpha.put("XINGTAI", "邢台             ");
        cityAlpha.put("XINGYI", "兴义              ");
        cityAlpha.put("XINING", "西宁              ");
        cityAlpha.put("XINXIANG", "新乡            ");
        cityAlpha.put("XINYANG", "信阳             ");
        cityAlpha.put("XINYU", "新余               ");
        cityAlpha.put("XINZHOU", "忻州             ");
        cityAlpha.put("SUQIAN", "宿迁              ");
        cityAlpha.put("SUYU", "宿豫                ");
        cityAlpha.put("SUZHOU1", "宿州             ");
        cityAlpha.put("XUANCHENG", "宣城           ");
        cityAlpha.put("XUCHANG", "许昌             ");
        cityAlpha.put("XUZHOU", "徐州              ");
        cityAlpha.put("YAAN", "雅安                ");
        cityAlpha.put("YAKESHI", "牙克石           ");
        cityAlpha.put("YANAN", "延安               ");
        cityAlpha.put("YANBIAN", "延边             ");
        cityAlpha.put("YANCHENG", "盐城            ");
        cityAlpha.put("YANGJIANG", "阳江           ");
        cityAlpha.put("YANGQUAN", "阳泉            ");
        cityAlpha.put("YANGZHOU", "扬州            ");
        cityAlpha.put("YANJI", "延吉               ");
        cityAlpha.put("YANTAI", "烟台              ");
        cityAlpha.put("YANZHOU", "兖州             ");
        cityAlpha.put("YIBIN", "宜宾               ");
        cityAlpha.put("YICHANG", "宜昌             ");
        cityAlpha.put("YICHUN", "宜春              ");
        cityAlpha.put("YICHUN1", "伊春             ");
        cityAlpha.put("YILI", "伊犁                ");
        cityAlpha.put("YINCHUAN", "银川            ");
        cityAlpha.put("YINGKOU", "营口             ");
        cityAlpha.put("YINGTAN", "鹰潭             ");
        cityAlpha.put("YINING", "伊宁              ");
        cityAlpha.put("YIWU", "义乌                ");
        cityAlpha.put("YIXING", "宜兴              ");
        cityAlpha.put("YIYANG", "益阳              ");
        cityAlpha.put("YONGKANG", "永康            ");
        cityAlpha.put("YONGZHOU", "永州            ");
        cityAlpha.put("YUEYANG", "岳阳             ");
        cityAlpha.put("YUHUAN", "玉环              ");
        cityAlpha.put("YULIN1", "榆林              ");
        cityAlpha.put("YULIN", "玉林               ");
        cityAlpha.put("YUNCHENG", "运城            ");
        cityAlpha.put("YUXI", "玉溪                ");
        cityAlpha.put("YUYAO", "余姚               ");
        cityAlpha.put("ZAOZHUANG", "枣庄           ");
        cityAlpha.put("ZENGCHENG", "增城           ");
        cityAlpha.put("CHANGCHUN", "长春           ");
        cityAlpha.put("CHANGHAI", "长海            ");
        cityAlpha.put("ZHANGJIAGANG", "张家港      ");
        cityAlpha.put("ZHANGJIAJIE", "张家界       ");
        cityAlpha.put("ZHANGJIAKOU", "张家口       ");
        cityAlpha.put("CHANGLE", "长乐             ");
        cityAlpha.put("ZHANGQIU", "章丘            ");
        cityAlpha.put("CHANGSHA", "长沙            ");
        cityAlpha.put("ZHANGYE", "张掖             ");
        cityAlpha.put("CHANGZHI", "长治            ");
        cityAlpha.put("ZHANGZHOU", "漳州           ");
        cityAlpha.put("ZHANJIANG", "湛江           ");
        cityAlpha.put("ZHAODONG", "肇东            ");
        cityAlpha.put("ZHAOQING", "肇庆            ");
        cityAlpha.put("ZHAOTONG", "昭通            ");
        cityAlpha.put("ZHENGZHOU", "郑州           ");
        cityAlpha.put("ZHENJIANG", "镇江           ");
        cityAlpha.put("ZHONGSHAN", "中山           ");
        cityAlpha.put("ZHOUKOU", "周口             ");
        cityAlpha.put("ZHOUSHAN", "舟山            ");
        cityAlpha.put("ZHUCHENG", "诸城            ");
        cityAlpha.put("ZHUHAI", "珠海              ");
        cityAlpha.put("ZHUJI", "诸暨               ");
        cityAlpha.put("ZHUMADIAN", "驻马店         ");
        cityAlpha.put("ZHUZHOU", "株洲             ");
        cityAlpha.put("ZIBO", "淄博                ");
        cityAlpha.put("ZIGONG", "自贡              ");
        cityAlpha.put("ZUNYI", "遵义               ");
        cityAlpha.put("WULUMUQI", "乌鲁木齐        ");
        cityAlpha.put("FUQING", "福清              ");
        cityAlpha.put("EZHOU", "鄂州               ");
        cityAlpha.put("BAOTOU", "包头              ");
        cityAlpha.put("XIAOSHAN", "萧山            ");
        cityAlpha.put("XUANHUA", "宣化             ");
        cityAlpha.put("JIANGYOU", "江油            ");
        cityAlpha.put("ZIYANG", "资阳              ");
        cityAlpha.put("XINJI", "辛集               ");
        cityAlpha.put("FOSHAN", "佛山              ");
        cityAlpha.put("WANZHOU", "万州             ");
        cityAlpha.put("ZOUCHENG", "邹城            ");
        cityAlpha.put("SHAOWU", "邵武              ");
        cityAlpha.put("JIANGYAN", "姜堰            ");
        cityAlpha.put("XIANGYIN", "湘阴            ");
        cityAlpha.put("SONGJIANG", "松江           ");
        cityAlpha.put("QITAIHE", "七台河           ");
        cityAlpha.put("LILING", "醴陵              ");
        cityAlpha.put("FULING", "涪陵              ");
        cityAlpha.put("GONGZHULING", "公主岭       ");
        cityAlpha.put("SHEXIAN", "歙县             ");
        cityAlpha.put("XINGHUA", "兴化             ");


        cityAlpha.put("GAOBEIDIAN", "高碑店");
        cityAlpha.put("TAIBEI", "台北");
        cityAlpha.put("GUIZHOU", "贵州省");
        cityAlpha.put("HARBIN", "哈尔滨");
//        cityAlpha.put("Sheung Shui", "香港上水");
//        cityAlpha.put("ZHONGXIN","高碑店");
    }

    public static String getCity(String alpha){
        String city = null;
        if (alpha == null || alpha.equals("") || alpha.equals("unknow")) {
            city = "";
        } else {
            city = cityAlpha.get(alpha.toUpperCase());
            if (city == null) {
                city = "";
            }
        }
        return city;
    }

    public static String getCityName(String alpha) {
        String city = null;
        if (alpha == null || alpha.equals("") || alpha.equals("unknow")) {
            city = "未分析出地域";
        } else {
            city = cityAlpha.get(alpha.toUpperCase());
            if (city == null) {
                city = alpha;
            } else {
                city = city.trim();
            }
        }
        return city;
    }
}
