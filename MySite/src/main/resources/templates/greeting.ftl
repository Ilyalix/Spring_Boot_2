<#import "parts/common.ftl" as c> <#-- ипортируем наш common, называем элиас - "С"-->
<#include "parts/security.ftl">

    <@c.page> <#--  используем шаблон page -->

        <#if isAdmin || isUser>
        <div class="navbar-text mr-3 ">Hi, ${name}</div>
        </#if>
    </@c.page>

