<script type="text/javascript" src="/js/plugins/ckeditor/ckeditor.js"></script>

<div class="container-fluid cm-container-white">
    <div class="panel panel-default">
        <div id="myTabs">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#base" aria-controls="base" role="tab" data-toggle="tab">基本信息</a></li>
                <li role="presentation"><a href="#detail" aria-controls="detail" role="tab" data-toggle="tab">商品详情</a></li>
                <li role="presentation"><a href="#image" aria-controls="image" role="tab" data-toggle="tab">商品相册</a></li>
                <li role="presentation"><a href="#property" aria-controls="property" role="tab" data-toggle="tab">商品属性</a></li>
            </ul>

            <!-- 提交商品保存表单 -->
            <form action="/products" method="post" id="productSaveForm">
                <!-- Tab panes -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="base">
                        <!-- 基本信息的页面 -->
                        <#if productVo??>
                            <#include "product_base_info_edit.ftl"/>
                        <#else>
                            <#include "product_base_info.ftl"/>
                        </#if>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="detail">
                        <!-- 商品详情 -->
                        <#include "product_details.ftl"/>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="image">
                        <!-- 商品相册 -->
                        <#if productVo??>
                            <#include "product_image_edit.ftl"/>
                        <#else>
                            <#include "product_image.ftl"/>
                        </#if>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="property">
                        <!--商品属性-->
                        <#if productVo??>
                            <#include "product_property_edit.ftl"/>
                        <#else>
                            <#include "product_property.ftl"/>
                        </#if>
                    </div>

                </div>
                <div class="modal-footer">

                    <button type="button" class="btn btn-primary" id="saveButton">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="application/javascript">
    $(function () {

        $("#saveButton").click(function () {
            var ckeditor = CKEDITOR.replace( 'details');
            //把ckedit的内容放到表单中
            $("#details").html(ckeditor.getData());

            $("#productSaveForm").ajaxSubmit({
                success:function (data) {
                    if(data.success){
                        $.messager.popup("保存成功");
                        $("#content").load("/products/view");
                    }else{
                        $.messager.alert("提示",data.errorMsg);
                    }
                }
            })
        });
        //判断新增商品还是修改商品
        <#if productVo??>
            $.get("/products/detail?productId=${productVo.product.id}",function (data) {
                var ckeditor = CKEDITOR.replace( 'details');
                ckeditor.setData(data);
            })
        <#else>
            var ckeditor = CKEDITOR.replace( 'details');
            ckeditor.setData("");
        </#if>
    })

    //选择商品分类，并且通过该分类获取分类下的属性和属性值
    function changeCatalog(obj) {
        var catalogId = $(obj).val();
        $("#productPropertyPanel").load("/catalogs/"+catalogId+"/propertys/propertyValues")
    }
</script>