<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>

<#--false -  показываем сслыку на регистрацию isRegisterForm-->
<@l.login "/login" false/>

</@c.page>