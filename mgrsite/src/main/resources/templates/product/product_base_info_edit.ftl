<div style="padding-bottom: 10px;padding-left: 10px;padding-right: 10px;padding-top: 10px;">
	<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label>商品名称</label> <input class="form-control"
					name="product.name" value="${productVo.product.name}">
			</div>
		</div>
		<div class="col-lg-6">
			<div class="form-group">
				<label>商品编号</label> <input class="form-control"
					name="product.code" value="${productVo.product.code}">
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label>所属品牌</label> <select class="form-control"
					name="product.brand.id">
				<option value="-1">请选择品牌</option>
				<#list brandList as brand >
					<#if brand.id == productVo.product.brand.id>
                    	<option value="${brand.id?c}" selected>${brand.name}</option>
					</#if>
				</#list>
				</select>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="form-group">
                <label>所属分类</label> <select class="form-control"
					id="catalogId" name="product.catalog.id" onchange="changeCatalog(this)">
                <option>请选择分类</option>
				<#list catalogList as catalog>
					<#if catalog.id == productVo.product.catalog.id>
                    	<option value="${catalog.id?c}" selected>${catalog.name}</option>
					</#if>
				</#list>
				</select>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label>市场售价</label> <input class="form-control"
					name="product.marketPrice" value="${productVo.product.marketPrice?c}">
			</div>
		</div>
		<div class="col-lg-6">
			<div class="form-group">
				<label>基础售价</label> <input class="form-control"
					name="product.basePrice" value="${productVo.product.basePrice?c}">
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="form-group">
				<label>商品关键字</label> <input class="form-control"
					placeholder="以逗号分隔" name="product.keyword" value="${productVo.product.keyword!''}">
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="form-group">
				<label>商品标签</label>
				<input class="form-control" placeholder="以逗号分隔"
					name="product.label" value="${productVo.product.label!''}"/>
			</div>
		</div>
	</div>
</div>