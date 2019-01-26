<link type="text/css" rel="stylesheet" href="/js/plugins/uploadifive/uploadifive.css" />
<script type="text/javascript" src="/js/plugins/uploadifive/jquery.uploadifive.min.js"></script>
<script type="application/javascript">
    $(function () {
        for(var i=0;i<8;i++){
            $("#butn"+i).uploadifive({
                buttonText:'上传商品图片',
                fileType: 'image',
                uploadScript:'/product/uploadImage',
                fileObjName:'img',
                onInit:function(){
                    $(".uploadifive-queue").hide();
                },
                onUploadComplete : function(file, data) {
                    $(this).closest(".image-div").children("img").attr("src",data);
                    $(this).closest(".image-div").children("input").val(data);
                }
            });
        }
    })
</script>

<div style="padding-bottom: 10px; padding-left: 10px; padding-right: 10px; padding-top: 10px;">
    <#macro productImage number>
        <div class="col-lg-3 col-md-6">
            <div class="image-div">
                <div>
                    <a href="javascript:;" id="butn${number}">上传</a>
                </div>
                <#if productVo.productImagesList[number]??>
                    <img style="width: 120px;height: 100px;margin-top: 5px;" src="${productVo.productImagesList[number].imagePath}">
                <#else>
                    <img style="width: 120px;height: 100px;margin-top: 5px;">
                </#if>
                <input type="hidden" name="productImagesList[${number}].imagePath">
            </div>
            <div class="input-group">
                <select class="form-control" style="width: 120px;margin-bottom: 15px;" name="productImagesList[${number}].sort">
                    <#list 1..8 as index>
                        <option value="${index}">${index}</option>
                    </#list>
                </select>
            </div>
        </div>
    </#macro>
    <div class="row">

    </div>
    <#if productVo.productImagesList?? && (productVo.productImagesList?size>0)>
        <#list 0..7 as number> <@productImage number/> </#list>
    </#if>

</div>