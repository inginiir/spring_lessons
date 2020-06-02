<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form class="form-inline" method="get" action="main">
                <input type="text" class="form-control" name="filter" placeholder="Search by tag" value="${filter!""}"/>
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <#if !message??>
        <#include "parts/messageEdit.ftl"/>
    </#if>
    <#include "parts/messageList.ftl"/>
</@c.page>