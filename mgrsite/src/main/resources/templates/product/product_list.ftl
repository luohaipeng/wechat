<script type="application/javascript">
    $(function () {
        $("#pagination_container").twbsPagination({
            totalPages:${(pageResult.totalPage == 0)?string('1','${pageResult.totalPage}')},
            visiblePages:${pageResult.pageSize},
            startPage:${pageResult.currentPage},
            first:"首页",
            prev:"上一页",
            next:"下一页",
            last:"末页",
            onPageClick:function(event,page){
                $("#currentPage").val(page);
                var param = $("#searchForm").serialize();
                $("#productList").load("/products?"+param);
            }
        });
    })
    function generateSku(id){
        $("#content").load("/products/"+id+"/skus")
    }
    function editProduct(id){
        $("#content").load("/products/"+id)
    }
</script>
<table class="table table-hover">
    <thead>
    <tr>
        <th>商品编号</th>
        <th>商品名称</th>
        <th>分类</th>
        <th>品牌</th>
        <th>市场价格</th>
        <th>基础价格</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody id="tbody">
   <#list pageResult.data as product>
    <tr>
        <td>${product.code}</td>
        <td><a href="#">${product.name}</a></td>
        <td>${product.catalog.name}</td>
        <td>
            <#if product.brand??>
                ${product.brand.name}
            </#if>
        </td>
        <td>${product.marketPrice}</td>
        <td>${product.basePrice}</td>
        <td>
            <a href="javascript:editProduct(${product.id?c})">编辑</a>
            | <a href="#">放入回收站</a>
           <#-- |<a href="#" onclick="generateSku(${product.id})">生成SKU</a>-->
        </td>
    </tr>
   </#list>
    </tbody>
</table>

<!--分页代码块-->
<div style="text-align: center;" id="pagination_container">

</div>