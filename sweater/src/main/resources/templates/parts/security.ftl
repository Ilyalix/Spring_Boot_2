<#--создаем приватность, скрываем list of user-->
<#--для определения переменных внутри freemaker используем синтаксис assign-->
<#assign
<#--создаем объект know, определен в контексте, значит мы можем работаь с сессией пользователя-->
know = Session.SPRING_SECURITY_CONTEXT??  <#--boolean -->

>

<#--если сессия существует,то выполяем опред.действия-->
<#if know>
<#--определяем набор необходимых данных например user -->
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal <#-- используем любые методы из объекта user -->

    isAdmin = user.isAdmin() <#-- если user является админом -->
    name = user.getUsername()

    isUser = user.isUser()
    name = user.getUsername()

    >
<#else>
    <#assign
    name = "unknow"
    isAdmin = false
    isUser = false
    >
</#if> <#-- иначе заглушка -->

