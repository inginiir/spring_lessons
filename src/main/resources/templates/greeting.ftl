<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
    <h5>Hello, <#if name !="unknown"> ${name}<#else> guest</#if></h5>
    <div>This is simple clone of twitter</div>
</@c.page>