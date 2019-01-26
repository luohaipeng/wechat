package com.petrel.controller;

import com.alibaba.fastjson.JSON;
import com.petrel.util.*;
import com.petrel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * 微信接口相关
 */
@Controller
public class WechatController {

    @Value("${wechat.token}")
    private String token;
    @Value("${wechat.appid}")
    private String appid;
    @Value("${wechat.secret}")
    private String secret;
    @Value("${wechat.nonceStr}")
    private String nonceStr;
    @Value("${wechat.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;
    /**
     * 接入微信验证
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @GetMapping(value = "/wechat")
    @ResponseBody
    public String wechat(String signature,String timestamp,String nonce,String echostr){

        return echostr;

        /*if(signature != null){
            //1）将token、timestamp、nonce三个参数进行字典序排序
            TreeSet<String> set = new TreeSet<String>();
            set.add(token);
            set.add(timestamp);
            set.add(nonce);
            //2）将三个参数字符串拼接成一个字符串进行sha1加密
            StringBuilder sb = new StringBuilder();
            for (String str : set){
                sb.append(str);
            }
            String sha1Str = SecurityUtil.SHA1(sb.toString());
            // 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
            if(signature.equals(sha1Str)){
                //如果相同的话就返回echostr
                return echostr;
            }
        }

        return "error";*/
    }

    /**
     * 提供给微信调用的接口
     * @param requestVo
     * @return
     */
    @PostMapping(value = "/wechat")
    @ResponseBody
    public Object wechat(@RequestBody WeChatRequest requestVo){
        System.out.println(requestVo);
        String msgType = requestVo.getMsgType();

        //根据不同的消息类型做不同的业务
        if(msgType.equals("text")){
            //如果是文本消息类型的话，响应一个“来小码哥”，并且把该消息保存到数据库
            WeChatResponse weChatResponse = new WeChatResponse();
            weChatResponse.setToUserName(requestVo.getFromUserName());
            weChatResponse.setFromUserName(requestVo.getToUserName());
            weChatResponse.setCreateTime(new Date().getTime()+"");
            weChatResponse.setMsgType("text");
            weChatResponse.setContent("来小码哥");
            String s = JAXBUtil.convertToXml(weChatResponse);
            System.out.println(s);
            return s;
        }else if(msgType.equals("event")){
            String event = requestVo.getEvent();
            if(event.equals("CLICK")){
                String eventKey = requestVo.getEventKey();
                if(eventKey.equals("11")){
                    WeChatResponse weChatResponse = new WeChatResponse();
                    weChatResponse.setToUserName(requestVo.getFromUserName());
                    weChatResponse.setFromUserName(requestVo.getToUserName());
                    weChatResponse.setCreateTime(new Date().getTime()+"");
                    weChatResponse.setMsgType("text");
                    weChatResponse.setContent("扫描点餐");
                    String s = JAXBUtil.convertToXml(weChatResponse);
                    return s;
                }
            }
        }
        return "success";
    }

    /**
     * 发送模板消息
     * @return
     */
    @RequestMapping("/sendTemplate")
    @ResponseBody
    public String sendTemplate(){
        TemplateVo templateVo = new TemplateVo();
        templateVo.setTemplate_id("XXNH5SPPYJuiXyx1KhRh0vEUJerVRvKJ-AD-fvnOFzI");
        templateVo.setTouser("o24_Yt0A8MwpG7YPgaNxXxiR_Km0");
        templateVo.setUrl("http://www.520it.com");

        HashMap<String, TemplateValueVo> templateValueMap = new HashMap<String, TemplateValueVo>();
        templateValueMap.put("first",new TemplateValueVo("尊敬的客户你好","#173177"));
        templateValueMap.put("keynote1",new TemplateValueVo("成功","#173177"));
        templateValueMap.put("keynote2",new TemplateValueVo("2017/10/13","#173177"));
        templateValueMap.put("keynote3",new TemplateValueVo("100","#173177"));
        templateValueMap.put("remark",new TemplateValueVo("欢迎再次光临","#173177"));

        templateVo.setData(templateValueMap);

        //发送模板消息的接口
        String sendTemplateUrl = UrlUtil.MESSAGE_TEMPLATE_SEND_URL.replace("ACCESS_TOKEN",
                GetAccessTonkenUtil.getAccessToken().getAccess_token());

        String templateResuletJSON = HttpUtil.post(sendTemplateUrl, JSON.toJSONString(templateVo));

        return templateResuletJSON;
    }

    /**
     * 自定义菜单
     * @return
     */
    @GetMapping("/customMemu")
    @ResponseBody
    public String customMemu(){
        //所有按钮
        Map button = new HashMap<String, List<CustomMenuVo>>();
        //存放一级菜单的按钮list
        List buttomList = new ArrayList<CustomMenuVo>();
        //一级菜单的第一个按钮
        CustomMenuVo customMenuVo1 = new CustomMenuVo("click","扫描点餐","11",null);
        //把一级菜单的第一个按钮添加到buttonList中
        buttomList.add(customMenuVo1);
        //一级菜单的第二个按钮
        CustomMenuVo customMenuVo2 = new CustomMenuVo("view","餐厅首页",null,url+"/product/index");
       /*  CustomMenuVo customMenuVo2 = new CustomMenuVo(null,"个人中心",null,null);
       //存放一级菜单的第二个按钮的子按钮list
        List subButtomList2 = new ArrayList<CustomMenuVo>();
        //一级菜单的第二个按钮的第一个按钮
        String url = UrlUtil.AUTHORIZE_URL
                .replace("APPID",appid)
                .replace("REDIRECT_URI","http://ctgtev.natappfree.cc/userInfo")
                .replace("SCOPE","snsapi_userinfo");
        CustomMenuVo customMenuVo21 = new CustomMenuVo("view","个人中心",null,url);
        //一级菜单的第二个按钮的第一个按钮添加到subButtomList2
        subButtomList2.add(customMenuVo21);
        //一级菜单的第二个按钮的第二个按钮
        CustomMenuVo customMenuVo22 = new CustomMenuVo("click","点赞","22",null);
        //一级菜单的第二个按钮的第二个按钮添加到subButtomList2
        subButtomList2.add(customMenuVo22);
        customMenuVo2.setSub_button(subButtomList2);*/
        //把一级菜单的第二个按钮添加到buttonList中
        buttomList.add(customMenuVo2);
        //一级菜单的第三个按钮
        CustomMenuVo customMenuVo3 = new CustomMenuVo("view","个人中心",null,url+"/user/index");
        //把一级菜单的第三个按钮添加到buttonList中
        buttomList.add(customMenuVo3);
        //把一级菜单的按钮添加到所有按钮的map中
        button.put("button",buttomList);
        //调用接口
        return restTemplate.postForObject(UrlUtil.MENU_CREATE_URL.replace("ACCESS_TOKEN",GetAccessTonkenUtil.getAccessToken().getAccess_token())
                ,JSON.toJSONString(button),String.class);
        /*return HttpUtil.post(UrlUtil.MENU_CREATE_URL.replace("ACCESS_TOKEN",GetAccessTonkenUtil.getAccessToken().getAccess_token())
                ,JSON.toJSONString(button));*/
    }

    /**
     * 自定义菜单中的个人中心（使用OAuth2.0开发授权获取用户的微信账号信息）
     * @param code
     * @param map
     * @return
     */
    @RequestMapping("/userInfo")
    public String userInfo(String code,Map map){
        //通过 code 换取网页授权 access_token（与基础支持中的 access_token 不同）
        String resultJson = HttpUtil.get(UrlUtil.OAUTH2_ACCESS_TOKEN_URL
                .replace("APPID", appid)
                .replace("CODE", code)
                .replace("SECRET",secret));
        OAuthAccessTokenVo oAuthAccessTokenVo = JSON.parseObject(resultJson, OAuthAccessTokenVo.class);
        //通过access_token和openid拉取用户信息
        String userInfoJson = HttpUtil.get(UrlUtil.USERINFO_URL
                .replace("ACCESS_TOKEN", oAuthAccessTokenVo.getAccess_token())
                .replace("OPENID", oAuthAccessTokenVo.getOpenid()));
        map.put("userInfo",userInfoJson);
        return "user_info";
    }

    /**
     * jssdk测试
     * @param map
     * @return
     */
    @RequestMapping("/jssdk")
    public String jssdk(Map map){

        //生成时间戳
        String timestamp = new Date().getTime()+"";

        //调用接口获取jsapi_ticket
        String resultTicketJson = HttpUtil.get(UrlUtil.JSAPI_TICKET_URL.replace("ACCESS_TOKEN",
                GetAccessTonkenUtil.getAccessToken().getAccess_token()));
        TicketVo ticketVo = JSON.parseObject(resultTicketJson, TicketVo.class);

        //算出签名
        TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
        treeMap.put("noncestr",nonceStr);
        treeMap.put("jsapi_ticket",ticketVo.getTicket());
        treeMap.put("timestamp",timestamp);
        treeMap.put("url",url+"/jssdk");

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String,Object> set : treeMap.entrySet()){
            sb.append(set.getKey()).append("=").append(set.getValue()).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        //使用sha1算法，得出signature
        String signature = SecurityUtil.SHA1(sb.toString());
        map.put("appId",appid);
        map.put("timestamp",timestamp);
        map.put("nonceStr",nonceStr);
        map.put("signature",signature);
        return "jssdk";
    }
}




