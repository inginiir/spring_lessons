<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as s>

<@c.page>
    <div>
        <@s.logout />
        <span> <a href="/user"> User list </a> </span>
    </div>
    <div>
        <form method="post">
            <input type="text" name="text" placeholder="Enter message"/>
            <input type="text" name="tag" placeholder="Tag"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Add</button>
        </form>
    </div>
    <div>List of message
    <form method="get" action="main">
        <input type="text" name="filter" value="${filter!""}">
        <button type="submit">Find</button>
    </form>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorName}</strong>
        </div>
    <#else>
        No messages
    </#list>
</@c.page>