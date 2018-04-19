package com.jesgoo.fast.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Desc
 * Created by xupeng on 2018/1/31.
 */

public class Message implements Serializable{

    /**
     * reason : 成功的返回
     * result : {"stat":"1","data":[{"uniquekey":"267479c534d73e66e9f81b7db77515b5","title":"娶了普京的女儿还敢出轨 找外遇？损失80%财产 但他还是表示感谢","date":"2018-01-31 09:55","category":"头条","author_name":"深度军事","url":"http://mini.eastday.com/mobile/180131095537096.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20180131/20180131095537_7f3d945df343681f11c59177a27d7c3a_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20180131/20180131095537_7f3d945df343681f11c59177a27d7c3a_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20180131/20180131095537_7f3d945df343681f11c59177a27d7c3a_1_mwpm_03200403.jpg"},{"uniquekey":"d844c1354251e17ed267014713eaef28","title":"大雪天，神农架金丝猴不差食儿","date":"2018-01-31 09:39","category":"头条","author_name":"国际在线","url":"http://mini.eastday.com/mobile/180131093911700.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20180131/20180131093911_a85cb46c2bf6d5b979d3531dcaba8f77_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20180131/20180131093911_bbc4236c13594e070bb4e9895f01a9b6_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20180131/20180131093911_a61fafa46bf5aacac7c670e9675eee55_1_mwpm_03200403.jpg"},{"uniquekey":"a7f0731d7a8539eb88daa84f1fc5d69a","title":"科学家培育出人猿杂交\u201c猩猩人\u201d，医生顾及道德舆论将它安乐死","date":"2018-01-31 09:36","category":"头条","author_name":"默默看国际","url":"http://mini.eastday.com/mobile/180131093622581.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20180131/20180131_6f59569f451f468d355e193e17988ca8_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20180131/20180131_9c52b36f7bb32eb2d572ba2a06165ebd_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20180131/20180131_b4e719f9a16b9e6a87ec6c770c6b466a_mwpm_03200403.jpg"},{"uniquekey":"a7ba45fd3a204ccab884ce7818272952","title":"印度女子嫁5个亲兄弟与\u201c老公\u201d轮流过夜 网友：真是奇葩习俗!","date":"2018-01-31 09:35","category":"头条","author_name":"大甜甜","url":"http://mini.eastday.com/mobile/180131093537903.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180131/20180131_27b331f214dc05833afb774e8b0d8da8_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20180131/20180131_99c5de32d04ef3c6d881ff0168bd0498_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20180131/20180131_8f0ede0bffd2b20091fe6012204d5506_mwpm_03200403.jpg"},{"uniquekey":"1286492241cde0c7df16aa702c8f8711","title":"台当局声称大陆游客赴台个人游成长 遭旅游业者打脸","date":"2018-01-31 09:27","category":"头条","author_name":"中国台湾网","url":"http://mini.eastday.com/mobile/180131092744000.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20180131/20180131092744_c6e8d31ad77ea58361dd78a88770c9ee_1_mwpm_03200403.jpg"},{"uniquekey":"e9ab5d6384bf4f70efcdd2a8b0cc5913","title":"曾花城副局长会见老挝人民革命党共青团中央访华代表团","date":"2018-01-31 09:17","category":"头条","author_name":"商务部国际经济合作事务局","url":"http://mini.eastday.com/mobile/180131091710713.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180131/20180131091710_67b04b26652d4455b507652e4b294249_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20180131/20180131091710_67b04b26652d4455b507652e4b294249_1_mwpm_03200403.jpg"},{"uniquekey":"e1eb3db722c7f71cf0fe31801c5487fc","title":"美军高官：选一个吧 我们没钱同时对付这俩","date":"2018-01-31 09:11","category":"头条","author_name":"观察者网","url":"http://mini.eastday.com/mobile/180131091132791.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180131/20180131091132_05edf821c0513a995470064263f9409a_5_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20180131/20180131091132_05edf821c0513a995470064263f9409a_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20180131/20180131091132_05edf821c0513a995470064263f9409a_3_mwpm_03200403.jpg"},{"uniquekey":"74d8b605d0200b95fcc923889eb5f795","title":"日本人为什么不睡床？看看日本和中国差别究竟在哪","date":"2018-01-31 09:07","category":"头条","author_name":"遗产君","url":"http://mini.eastday.com/mobile/180131090731164.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20180131/20180131090731_41b60f3b31fcfb04d128ae3ba5694126_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20180131/20180131090731_41b60f3b31fcfb04d128ae3ba5694126_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20180131/20180131090731_41b60f3b31fcfb04d128ae3ba5694126_2_mwpm_03200403.jpg"},{"uniquekey":"70ec73352a4ff486aca42b8fdae9092d","title":"废弃锅炉房变身全新求知站 南汇公交打造职工文化交流驿站","date":"2018-01-31 09:03","category":"头条","author_name":"新民网","url":"http://mini.eastday.com/mobile/180131090307585.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20180131/20180131090307_2328594b6c4fbe66b1306d9156d36660_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20180131/20180131090307_2328594b6c4fbe66b1306d9156d36660_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20180131/20180131090307_2328594b6c4fbe66b1306d9156d36660_1_mwpm_03200403.jpg"},{"uniquekey":"c4f0844816315cbb7520fec434bdb6d5","title":"中企1月中旬已根据联合国决议撤离朝鲜?中方回应","date":"2018-01-31 08:59","category":"头条","author_name":"海外网","url":"http://mini.eastday.com/mobile/180131085929234.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20180131/20180131085929_da0b0d52ceceb5c349596e6577f0f1e1_1_mwpm_03200403.jpg"},{"uniquekey":"4c94fd607b4ba2ce76d2b6f5f8776e8c","title":"\u201c佛系\u201d邮递员懒出新高度，8年里私扣573公斤邮件","date":"2018-01-31 08:56","category":"头条","author_name":"猴子不语","url":"http://mini.eastday.com/mobile/180131085657262.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20180131/20180131085657_d9c138f0aa7411ce9c4853844ededa66_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20180131/20180131085657_d9c138f0aa7411ce9c4853844ededa66_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20180131/20180131085657_d9c138f0aa7411ce9c4853844ededa66_2_mwpm_03200403.jpg"},{"uniquekey":"2ec793bf8de465c96898365ecc9928b9","title":"英国首相特雷莎·梅飞抵武汉开启访华之旅","date":"2018-01-31 08:52","category":"头条","author_name":"央视网","url":"http://mini.eastday.com/mobile/180131085242400.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180131/20180131085242_04df6d5927d718e42dfcb7a380843a12_1_mwpm_03200403.jpg"},{"uniquekey":"ad0194bb40fdcb847e6e730940d87b68","title":"活泼可爱的小公主 每天给妈妈念儿歌","date":"2018-01-31 08:41","category":"头条","author_name":"大众网","url":"http://mini.eastday.com/mobile/180131084124124.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180131/20180131084124_fdb6806690ffe27f7a571d7091ed243e_1_mwpm_03200403.jpg"},{"uniquekey":"5d9c48bfd8423e0852314bdee7971975","title":"变本加厉！台当局威胁砍两岸定期航班 被批无下限","date":"2018-01-31 08:35","category":"头条","author_name":"海外网","url":"http://mini.eastday.com/mobile/180131083553329.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20180131/20180131083553_18691c97035ddbb9218fbcdc80233f38_1_mwpm_03200403.jpg"},{"uniquekey":"b60d84dabf44af9e5538ae59680c5c8f","title":"你就是我最想要的人！让12星座男想跟你求婚的瞬间！","date":"2018-01-31 08:19","category":"头条","author_name":"星座百科","url":"http://mini.eastday.com/mobile/180131081951398.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20180131/20180131081951_e7bacdbedb27313c301b9c0543992e39_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20180131/20180131081951_e7bacdbedb27313c301b9c0543992e39_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20180131/20180131081951_e7bacdbedb27313c301b9c0543992e39_1_mwpm_03200403.jpg"},{"uniquekey":"f0a4512ee6fe3b443a5681566b7b7441","title":"美国航天局：国际空间站俄宇航员2月2日将出舱","date":"2018-01-31 08:19","category":"头条","author_name":"中国新闻网","url":"http://mini.eastday.com/mobile/180131081947080.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20180131/20180131081947_0895025b765adba463e237b7bdf3b2ae_1_mwpm_03200403.jpg"},{"uniquekey":"3f17b82e1b3bf389e8c8f02f6e2dfa99","title":"日本学者推演\u201c美中海战\u201d 还给日本支三招！","date":"2018-01-31 08:10","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/180131081026653.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20180131/20180131081026_d01520f78515e9794f2e2539d9d88c6a_1_mwpm_03200403.jpg"},{"uniquekey":"dec12f746a9ca94196de86e1922ec03b","title":"相撞风险增加？俄美军机再次危险接近：1.5 米！","date":"2018-01-31 08:10","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/180131081026536.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20180131/20180131081026_68cb2e8e63da55028c571fe91f3d2a22_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20180131/20180131081026_68cb2e8e63da55028c571fe91f3d2a22_1_mwpm_03200403.jpg"},{"uniquekey":"93d9230b8d03742280fbc5340548386e","title":"老板去世情人带孩子来争遗产 亲子鉴定让情人傻眼","date":"2018-01-31 08:07","category":"头条","author_name":" 钱江晚报","url":"http://mini.eastday.com/mobile/180131080755940.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20180131/20180131080755_b309822c7121f90503de22f3b1d1a5cb_1_mwpm_03200403.jpg"},{"uniquekey":"abf4c6440914e659a5069ba63f2aeddd","title":"迎风斗雪，航空兵与复杂气象来了一场正面交锋！","date":"2018-01-31 08:04","category":"头条","author_name":"中国军网","url":"http://mini.eastday.com/mobile/180131080425559.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20180131/20180131080425_f3daff5e15d9206d8dc544c571269eb7_5_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20180131/20180131080425_e1875e966f4da8be2998200c7475916f_7_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20180131/20180131080425_bc5bf1ce452821b9c1f81c2e80f8fe8d_1_mwpm_03200403.jpg"},{"uniquekey":"01055949a21764748f80716b9639951e","title":"时代楷模\u2014\u2014武汉精神践行者 2017年度人物榜单发布","date":"2018-01-31 07:59","category":"头条","author_name":"长江网","url":"http://mini.eastday.com/mobile/180131075951892.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180131/20180131075951_9ace377813dfa6e5b9b567f899a6d614_7_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20180131/20180131075951_9ace377813dfa6e5b9b567f899a6d614_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20180131/20180131075951_9ace377813dfa6e5b9b567f899a6d614_2_mwpm_03200403.jpg"},{"uniquekey":"48ea18e66837b0f2540d372b2d26b85d","title":"\"超级蓝月\"+月全食今晚现身！2018观天攻略知多少","date":"2018-01-31 07:43","category":"头条","author_name":"中国新闻网","url":"http://mini.eastday.com/mobile/180131074335830.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20180131/20180131074335_3b576a73b452bed48ca5d49044f54650_1_mwpm_03200403.jpg"},{"uniquekey":"9abde411a673363ddb3add2ded4ce2dc","title":"注意！空气重污染预警刚刚升级为黄色，一系列应急措施启动","date":"2018-01-31 07:40","category":"头条","author_name":"上海发布","url":"http://mini.eastday.com/mobile/180131074047258.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20180131/20180131074047_e908d4ded6fbc8cd8194787668bfaf46_1_mwpm_03200403.jpg"},{"uniquekey":"19febe672591a7c98fcf0c8bb7d43ca9","title":"台媒：林心如真\u201c解独\u201d 登北京春晚唱《深情相拥》","date":"2018-01-31 07:32","category":"头条","author_name":"中国青年网","url":"http://mini.eastday.com/mobile/180131073208006.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180131/20180131073208_46fa49973e5e01444f7f79bbb919f19e_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20180131/20180131073208_46fa49973e5e01444f7f79bbb919f19e_2_mwpm_03200403.jpg"},{"uniquekey":"cc4fc909fb8545c33865e79eb51ff132","title":"美国大都会人寿拟出售中国香港业务 可能是内资企业接手","date":"2018-01-31 07:21","category":"头条","author_name":"中国经济网","url":"http://mini.eastday.com/mobile/180131072130795.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20180131/20180131072130_bd1dc189ea10870a79951ec0241c1d0f_1_mwpm_03200403.jpg"},{"uniquekey":"935af39ef65501fe8861135e0ae1e0e3","title":"专家：今年煤炭行业兼并重组或有三大发力点","date":"2018-01-31 07:21","category":"头条","author_name":"中国经济网","url":"http://mini.eastday.com/mobile/180131072126753.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20180131/20180131072126_00496889c6c532328c70ea3fce130511_1_mwpm_03200403.jpg"},{"uniquekey":"4f4e403af296ace1074d8adc64c98a3a","title":"下乡代表聚首\"乡村振兴\" \"三乡工程\"让村民获得感更强","date":"2018-01-31 07:18","category":"头条","author_name":"长江网","url":"http://mini.eastday.com/mobile/180131071832204.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20180131/20180131071832_f1bee077958fa360ed082442d9a4d48c_1_mwpm_03200403.jpg"},{"uniquekey":"ceb8d9334b19d58e19186e4c545d337a","title":"人工智能+ 商贸无界化(聚焦高质量发展·关注人工智能③)","date":"2018-01-31 07:17","category":"头条","author_name":"人民网","url":"http://mini.eastday.com/mobile/180131071726547.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20180131/20180131071726_a7f7cfb147e7c8a020b60a1ec16f43cf_1_mwpm_03200403.jpg"},{"uniquekey":"e356a6e2ecb60b10a2828500a520031a","title":"美国加州一直升机坠毁并砸中民宅 造成3死2伤","date":"2018-01-31 07:15","category":"头条","author_name":"海外网","url":"http://mini.eastday.com/mobile/180131071533743.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20180131/20180131071533_28a563be12e2c71c1df44733525f38fd_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20180131/20180131071533_28a563be12e2c71c1df44733525f38fd_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20180131/20180131071533_28a563be12e2c71c1df44733525f38fd_3_mwpm_03200403.jpg"},{"uniquekey":"676062aea2048c459ff366e2c5fb5ddd","title":"猝不及防！美国大兵就这样曝光了自家机密以及\u2026\u2026","date":"2018-01-31 07:12","category":"头条","author_name":"杭州网","url":"http://mini.eastday.com/mobile/180131071257473.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20180131/20180131071257_4a4abc6dacd5e3ab8b15c9c6cdbdd618_5_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20180131/20180131071257_fd9f5ef6a3fed5fd2fc483984ece1638_8_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20180131/20180131071257_d39c67e67aa6e2c2b2b0b9c6c2794cda_1_mwpm_03200403.jpg"}]}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean implements Serializable{
        /**
         * stat : 1
         * data : [{"uniquekey":"267479c534d73e66e9f81b7db77515b5","title":"娶了普京的女儿还敢出轨 找外遇？损失80%财产 但他还是表示感谢","date":"2018-01-31 09:55","category":"头条","author_name":"深度军事","url":"http://mini.eastday.com/mobile/180131095537096.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20180131/20180131095537_7f3d945df343681f11c59177a27d7c3a_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20180131/20180131095537_7f3d945df343681f11c59177a27d7c3a_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20180131/20180131095537_7f3d945df343681f11c59177a27d7c3a_1_mwpm_03200403.jpg"},{"uniquekey":"d844c1354251e17ed267014713eaef28","title":"大雪天，神农架金丝猴不差食儿","date":"2018-01-31 09:39","category":"头条","author_name":"国际在线","url":"http://mini.eastday.com/mobile/180131093911700.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20180131/20180131093911_a85cb46c2bf6d5b979d3531dcaba8f77_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20180131/20180131093911_bbc4236c13594e070bb4e9895f01a9b6_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20180131/20180131093911_a61fafa46bf5aacac7c670e9675eee55_1_mwpm_03200403.jpg"},{"uniquekey":"a7f0731d7a8539eb88daa84f1fc5d69a","title":"科学家培育出人猿杂交\u201c猩猩人\u201d，医生顾及道德舆论将它安乐死","date":"2018-01-31 09:36","category":"头条","author_name":"默默看国际","url":"http://mini.eastday.com/mobile/180131093622581.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20180131/20180131_6f59569f451f468d355e193e17988ca8_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20180131/20180131_9c52b36f7bb32eb2d572ba2a06165ebd_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20180131/20180131_b4e719f9a16b9e6a87ec6c770c6b466a_mwpm_03200403.jpg"},{"uniquekey":"a7ba45fd3a204ccab884ce7818272952","title":"印度女子嫁5个亲兄弟与\u201c老公\u201d轮流过夜 网友：真是奇葩习俗!","date":"2018-01-31 09:35","category":"头条","author_name":"大甜甜","url":"http://mini.eastday.com/mobile/180131093537903.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180131/20180131_27b331f214dc05833afb774e8b0d8da8_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20180131/20180131_99c5de32d04ef3c6d881ff0168bd0498_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20180131/20180131_8f0ede0bffd2b20091fe6012204d5506_mwpm_03200403.jpg"},{"uniquekey":"1286492241cde0c7df16aa702c8f8711","title":"台当局声称大陆游客赴台个人游成长 遭旅游业者打脸","date":"2018-01-31 09:27","category":"头条","author_name":"中国台湾网","url":"http://mini.eastday.com/mobile/180131092744000.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20180131/20180131092744_c6e8d31ad77ea58361dd78a88770c9ee_1_mwpm_03200403.jpg"},{"uniquekey":"e9ab5d6384bf4f70efcdd2a8b0cc5913","title":"曾花城副局长会见老挝人民革命党共青团中央访华代表团","date":"2018-01-31 09:17","category":"头条","author_name":"商务部国际经济合作事务局","url":"http://mini.eastday.com/mobile/180131091710713.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180131/20180131091710_67b04b26652d4455b507652e4b294249_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20180131/20180131091710_67b04b26652d4455b507652e4b294249_1_mwpm_03200403.jpg"},{"uniquekey":"e1eb3db722c7f71cf0fe31801c5487fc","title":"美军高官：选一个吧 我们没钱同时对付这俩","date":"2018-01-31 09:11","category":"头条","author_name":"观察者网","url":"http://mini.eastday.com/mobile/180131091132791.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180131/20180131091132_05edf821c0513a995470064263f9409a_5_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20180131/20180131091132_05edf821c0513a995470064263f9409a_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20180131/20180131091132_05edf821c0513a995470064263f9409a_3_mwpm_03200403.jpg"},{"uniquekey":"74d8b605d0200b95fcc923889eb5f795","title":"日本人为什么不睡床？看看日本和中国差别究竟在哪","date":"2018-01-31 09:07","category":"头条","author_name":"遗产君","url":"http://mini.eastday.com/mobile/180131090731164.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20180131/20180131090731_41b60f3b31fcfb04d128ae3ba5694126_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20180131/20180131090731_41b60f3b31fcfb04d128ae3ba5694126_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20180131/20180131090731_41b60f3b31fcfb04d128ae3ba5694126_2_mwpm_03200403.jpg"},{"uniquekey":"70ec73352a4ff486aca42b8fdae9092d","title":"废弃锅炉房变身全新求知站 南汇公交打造职工文化交流驿站","date":"2018-01-31 09:03","category":"头条","author_name":"新民网","url":"http://mini.eastday.com/mobile/180131090307585.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20180131/20180131090307_2328594b6c4fbe66b1306d9156d36660_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20180131/20180131090307_2328594b6c4fbe66b1306d9156d36660_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20180131/20180131090307_2328594b6c4fbe66b1306d9156d36660_1_mwpm_03200403.jpg"},{"uniquekey":"c4f0844816315cbb7520fec434bdb6d5","title":"中企1月中旬已根据联合国决议撤离朝鲜?中方回应","date":"2018-01-31 08:59","category":"头条","author_name":"海外网","url":"http://mini.eastday.com/mobile/180131085929234.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20180131/20180131085929_da0b0d52ceceb5c349596e6577f0f1e1_1_mwpm_03200403.jpg"},{"uniquekey":"4c94fd607b4ba2ce76d2b6f5f8776e8c","title":"\u201c佛系\u201d邮递员懒出新高度，8年里私扣573公斤邮件","date":"2018-01-31 08:56","category":"头条","author_name":"猴子不语","url":"http://mini.eastday.com/mobile/180131085657262.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20180131/20180131085657_d9c138f0aa7411ce9c4853844ededa66_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20180131/20180131085657_d9c138f0aa7411ce9c4853844ededa66_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20180131/20180131085657_d9c138f0aa7411ce9c4853844ededa66_2_mwpm_03200403.jpg"},{"uniquekey":"2ec793bf8de465c96898365ecc9928b9","title":"英国首相特雷莎·梅飞抵武汉开启访华之旅","date":"2018-01-31 08:52","category":"头条","author_name":"央视网","url":"http://mini.eastday.com/mobile/180131085242400.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180131/20180131085242_04df6d5927d718e42dfcb7a380843a12_1_mwpm_03200403.jpg"},{"uniquekey":"ad0194bb40fdcb847e6e730940d87b68","title":"活泼可爱的小公主 每天给妈妈念儿歌","date":"2018-01-31 08:41","category":"头条","author_name":"大众网","url":"http://mini.eastday.com/mobile/180131084124124.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180131/20180131084124_fdb6806690ffe27f7a571d7091ed243e_1_mwpm_03200403.jpg"},{"uniquekey":"5d9c48bfd8423e0852314bdee7971975","title":"变本加厉！台当局威胁砍两岸定期航班 被批无下限","date":"2018-01-31 08:35","category":"头条","author_name":"海外网","url":"http://mini.eastday.com/mobile/180131083553329.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20180131/20180131083553_18691c97035ddbb9218fbcdc80233f38_1_mwpm_03200403.jpg"},{"uniquekey":"b60d84dabf44af9e5538ae59680c5c8f","title":"你就是我最想要的人！让12星座男想跟你求婚的瞬间！","date":"2018-01-31 08:19","category":"头条","author_name":"星座百科","url":"http://mini.eastday.com/mobile/180131081951398.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20180131/20180131081951_e7bacdbedb27313c301b9c0543992e39_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20180131/20180131081951_e7bacdbedb27313c301b9c0543992e39_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20180131/20180131081951_e7bacdbedb27313c301b9c0543992e39_1_mwpm_03200403.jpg"},{"uniquekey":"f0a4512ee6fe3b443a5681566b7b7441","title":"美国航天局：国际空间站俄宇航员2月2日将出舱","date":"2018-01-31 08:19","category":"头条","author_name":"中国新闻网","url":"http://mini.eastday.com/mobile/180131081947080.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20180131/20180131081947_0895025b765adba463e237b7bdf3b2ae_1_mwpm_03200403.jpg"},{"uniquekey":"3f17b82e1b3bf389e8c8f02f6e2dfa99","title":"日本学者推演\u201c美中海战\u201d 还给日本支三招！","date":"2018-01-31 08:10","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/180131081026653.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20180131/20180131081026_d01520f78515e9794f2e2539d9d88c6a_1_mwpm_03200403.jpg"},{"uniquekey":"dec12f746a9ca94196de86e1922ec03b","title":"相撞风险增加？俄美军机再次危险接近：1.5 米！","date":"2018-01-31 08:10","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/180131081026536.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20180131/20180131081026_68cb2e8e63da55028c571fe91f3d2a22_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20180131/20180131081026_68cb2e8e63da55028c571fe91f3d2a22_1_mwpm_03200403.jpg"},{"uniquekey":"93d9230b8d03742280fbc5340548386e","title":"老板去世情人带孩子来争遗产 亲子鉴定让情人傻眼","date":"2018-01-31 08:07","category":"头条","author_name":" 钱江晚报","url":"http://mini.eastday.com/mobile/180131080755940.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20180131/20180131080755_b309822c7121f90503de22f3b1d1a5cb_1_mwpm_03200403.jpg"},{"uniquekey":"abf4c6440914e659a5069ba63f2aeddd","title":"迎风斗雪，航空兵与复杂气象来了一场正面交锋！","date":"2018-01-31 08:04","category":"头条","author_name":"中国军网","url":"http://mini.eastday.com/mobile/180131080425559.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20180131/20180131080425_f3daff5e15d9206d8dc544c571269eb7_5_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20180131/20180131080425_e1875e966f4da8be2998200c7475916f_7_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20180131/20180131080425_bc5bf1ce452821b9c1f81c2e80f8fe8d_1_mwpm_03200403.jpg"},{"uniquekey":"01055949a21764748f80716b9639951e","title":"时代楷模\u2014\u2014武汉精神践行者 2017年度人物榜单发布","date":"2018-01-31 07:59","category":"头条","author_name":"长江网","url":"http://mini.eastday.com/mobile/180131075951892.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180131/20180131075951_9ace377813dfa6e5b9b567f899a6d614_7_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20180131/20180131075951_9ace377813dfa6e5b9b567f899a6d614_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20180131/20180131075951_9ace377813dfa6e5b9b567f899a6d614_2_mwpm_03200403.jpg"},{"uniquekey":"48ea18e66837b0f2540d372b2d26b85d","title":"\"超级蓝月\"+月全食今晚现身！2018观天攻略知多少","date":"2018-01-31 07:43","category":"头条","author_name":"中国新闻网","url":"http://mini.eastday.com/mobile/180131074335830.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20180131/20180131074335_3b576a73b452bed48ca5d49044f54650_1_mwpm_03200403.jpg"},{"uniquekey":"9abde411a673363ddb3add2ded4ce2dc","title":"注意！空气重污染预警刚刚升级为黄色，一系列应急措施启动","date":"2018-01-31 07:40","category":"头条","author_name":"上海发布","url":"http://mini.eastday.com/mobile/180131074047258.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20180131/20180131074047_e908d4ded6fbc8cd8194787668bfaf46_1_mwpm_03200403.jpg"},{"uniquekey":"19febe672591a7c98fcf0c8bb7d43ca9","title":"台媒：林心如真\u201c解独\u201d 登北京春晚唱《深情相拥》","date":"2018-01-31 07:32","category":"头条","author_name":"中国青年网","url":"http://mini.eastday.com/mobile/180131073208006.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180131/20180131073208_46fa49973e5e01444f7f79bbb919f19e_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20180131/20180131073208_46fa49973e5e01444f7f79bbb919f19e_2_mwpm_03200403.jpg"},{"uniquekey":"cc4fc909fb8545c33865e79eb51ff132","title":"美国大都会人寿拟出售中国香港业务 可能是内资企业接手","date":"2018-01-31 07:21","category":"头条","author_name":"中国经济网","url":"http://mini.eastday.com/mobile/180131072130795.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20180131/20180131072130_bd1dc189ea10870a79951ec0241c1d0f_1_mwpm_03200403.jpg"},{"uniquekey":"935af39ef65501fe8861135e0ae1e0e3","title":"专家：今年煤炭行业兼并重组或有三大发力点","date":"2018-01-31 07:21","category":"头条","author_name":"中国经济网","url":"http://mini.eastday.com/mobile/180131072126753.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20180131/20180131072126_00496889c6c532328c70ea3fce130511_1_mwpm_03200403.jpg"},{"uniquekey":"4f4e403af296ace1074d8adc64c98a3a","title":"下乡代表聚首\"乡村振兴\" \"三乡工程\"让村民获得感更强","date":"2018-01-31 07:18","category":"头条","author_name":"长江网","url":"http://mini.eastday.com/mobile/180131071832204.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20180131/20180131071832_f1bee077958fa360ed082442d9a4d48c_1_mwpm_03200403.jpg"},{"uniquekey":"ceb8d9334b19d58e19186e4c545d337a","title":"人工智能+ 商贸无界化(聚焦高质量发展·关注人工智能③)","date":"2018-01-31 07:17","category":"头条","author_name":"人民网","url":"http://mini.eastday.com/mobile/180131071726547.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20180131/20180131071726_a7f7cfb147e7c8a020b60a1ec16f43cf_1_mwpm_03200403.jpg"},{"uniquekey":"e356a6e2ecb60b10a2828500a520031a","title":"美国加州一直升机坠毁并砸中民宅 造成3死2伤","date":"2018-01-31 07:15","category":"头条","author_name":"海外网","url":"http://mini.eastday.com/mobile/180131071533743.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20180131/20180131071533_28a563be12e2c71c1df44733525f38fd_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20180131/20180131071533_28a563be12e2c71c1df44733525f38fd_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20180131/20180131071533_28a563be12e2c71c1df44733525f38fd_3_mwpm_03200403.jpg"},{"uniquekey":"676062aea2048c459ff366e2c5fb5ddd","title":"猝不及防！美国大兵就这样曝光了自家机密以及\u2026\u2026","date":"2018-01-31 07:12","category":"头条","author_name":"杭州网","url":"http://mini.eastday.com/mobile/180131071257473.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20180131/20180131071257_4a4abc6dacd5e3ab8b15c9c6cdbdd618_5_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20180131/20180131071257_fd9f5ef6a3fed5fd2fc483984ece1638_8_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20180131/20180131071257_d39c67e67aa6e2c2b2b0b9c6c2794cda_1_mwpm_03200403.jpg"}]
         */

        private String stat;
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Serializable{
            /**
             * uniquekey : 267479c534d73e66e9f81b7db77515b5
             * title : 娶了普京的女儿还敢出轨 找外遇？损失80%财产 但他还是表示感谢
             * date : 2018-01-31 09:55
             * category : 头条
             * author_name : 深度军事
             * url : http://mini.eastday.com/mobile/180131095537096.html
             * thumbnail_pic_s : http://06.imgmini.eastday.com/mobile/20180131/20180131095537_7f3d945df343681f11c59177a27d7c3a_3_mwpm_03200403.jpg
             * thumbnail_pic_s02 : http://06.imgmini.eastday.com/mobile/20180131/20180131095537_7f3d945df343681f11c59177a27d7c3a_2_mwpm_03200403.jpg
             * thumbnail_pic_s03 : http://06.imgmini.eastday.com/mobile/20180131/20180131095537_7f3d945df343681f11c59177a27d7c3a_1_mwpm_03200403.jpg
             */

            private String uniquekey;
            private String title;
            private String date;
            private String category;
            private String author_name;
            private String url;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }
        }
    }
}
