<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form class="form-inline" method="get" action="main">
                <input type="text" class="form-control" name="filter" placeholder="Search by tag" value="${filter!""}">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>
    <a class="btn btn-primary mb-2" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new message
    </a>
    <div class="form-group">
        <form method="post" enctype="multipart/form-data" class="collapse" id="collapseExample">
            <input type="text" class="form-control mb-2" name="text" placeholder="Enter message"/>
            <input type="text" class="form-control mb-2" name="tag" placeholder="Tag"/>
            <div class="custom-file mb-2">
                <input type="file" name="file" id="customFile">
                <label class="custom-file-label " for="customFile">Choose file</label>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary mb-2">Add</button>
        </form>
    </div>

    <div class="card-columns">
        <#list messages as message>
            <div class="card my-3">
                <#if message.filename??>
                    <img src="/img/${message.filename}" class="card-img-top">
                </#if>
                <div class="m-2">
                    <span>${message.text}</span>
                    <i>${message.tag}</i>
                </div>
                <div class="card-footer text-muted">
                    ${message.authorName}
                </div>
            </div>
        <#else>
            No messages
        </#list>
    </div>
</@c.page>