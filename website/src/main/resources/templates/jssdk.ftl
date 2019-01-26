<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">

    <meta name="description" content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.
">
    <title>Title</title>
    <link rel="stylesheet" href="/css/weui.min.css">
    <link rel="stylesheet" href="/css/jquery-weui.min.css">
    <script type="text/javascript" src="/js/jweixin-1.2.0.js"></script>

    <script type="text/javascript">
        wx.config({
            debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: '${appId}', // 必填，公众号的唯一标识
            timestamp: ${timestamp}, // 必填，生成签名的时间戳
            nonceStr: '${nonceStr}', // 必填，生成签名的随机串
            signature: '${signature}',// 必填，签名，见附录1
            jsApiList: ['scanQRCode'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });

        function openScanCode() {
            wx.scanQRCode({
                needResult: 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
                scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
                success: function (res) {
                    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
                }
            });
        }
    </script>
</head>
<body>
<input type="button" value="打开二维码" onclick="openScanCode()">

<div class="weui-grids">
    <a href="" class="weui-grid js_grid">
        <div class="weui-grid__icon">
            <img src="/images/icon_nav_button.png" alt="">
        </div>
        <p class="weui-grid__label">
            Button
        </p>
    </a>
    <a href="" class="weui-grid js_grid">
        <div class="weui-grid__icon">
            <img src="/images/icon_nav_cell.png" alt="">
        </div>
        <p class="weui-grid__label">
            List
        </p>
    </a>
    <a href="" class="weui-grid js_grid">
        <div class="weui-grid__icon">
            <img src="/images/icon_nav_cell.png" alt="">
        </div>
        <p class="weui-grid__label">
            Form
        </p>
    </a>
    <a href="" class="weui-grid js_grid">
        <div class="weui-grid__icon">
            <img src="/images/icon_nav_cell.png" alt="">
        </div>
        <p class="weui-grid__label">
            Flex
        </p>
    </a>
    <a href="" class="weui-grid js_grid">
        <div class="weui-grid__icon">
            <img src="/images/icon_nav_toast.png" alt="">
        </div>
        <p class="weui-grid__label">
            Toast
        </p>
    </a>
    <a href="" class="weui-grid js_grid">
        <div class="weui-grid__icon">
            <img src="/images/icon_nav_dialog.png" alt="">
        </div>
        <p class="weui-grid__label">
            Dialog
        </p>
    </a>
    <a href="" class="weui-grid js_grid">
        <div class="weui-grid__icon">
            <img src="/images/icon_nav_progress.png" alt="">
        </div>
        <p class="weui-grid__label">
            Progress
        </p>
    </a>
    <a href="" class="weui-grid js_grid">
        <div class="weui-grid__icon">
            <img src="/images/icon_nav_msg.png" alt="">
        </div>
        <p class="weui-grid__label">
            Msg
        </p>
    </a>
    <a href="" class="weui-grid js_grid">
        <div class="weui-grid__icon">
            <img src="/images/icon_nav_article.png" alt="">
        </div>
        <p class="weui-grid__label">
            Article
        </p>
    </a>
</div>
<script src="/js/jquery.min.js"></script>
<script src="/js/jquery-weui.min.js"></script>
</body>
</html>