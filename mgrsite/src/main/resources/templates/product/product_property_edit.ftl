<div style="padding-bottom: 10px; padding-left: 10px; padding-right: 10px; padding-top: 10px;">
    <table class="table table-bordered catalog-property-table">
        <thead>
        <tr>
            <th>名称</th>
            <th>值</th>
        </tr>
        </thead>
        <tbody id="productPropertyPanel">
            <#list propertyList as property>
            <tr>
                <td>
                    ${property.name}
                    <input type="hidden"  name="productPropertyValueList[${property_index}].name" value="${property.name}">
                </td>
                <td>
                <#if property.type == 1>
                    <select class="form-control" name="productPropertyValueList[${property_index}].value">
                        <#list property.propertyValueList as proVal>
                            <option value="${proVal.value}">${proVal.value}</option>
                        </#list>
                    </select>
                <#else>
                    <input name="productPropertyValueList[${property_index}].value" class="form-control">
                </#if>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>