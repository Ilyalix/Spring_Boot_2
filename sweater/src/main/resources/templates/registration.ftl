<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
Add new user


    <@l.login "/registration" true/>

<div class="container mt-3">
<#--// если нет сообщений падений не произойдет-->
    ${message?ifExists}

</@c.page>