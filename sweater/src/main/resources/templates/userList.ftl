<#import "parts/common.ftl" as c> <#-- ипортируем наш common, называем элиас - "С"-->

<@c.page> <#-- С используем шаблон page -->

   List of users
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th></th>
    </tr>
    </thead>
    <body>

    <#list users as us>  <#-- выводим всех пользователей! -->
    <tr>
        <td>${us.username}</td>
        <td><#list us.roles as role>${role}<#sep>, </#list></td> <#-- выводим все роли, которые могут быть у ПОЛЬЗОВАТЕЛЯ! -->
        <td><a href="/user/${us.id}">&nbsp Edit</a></td>
    </tr>
    </#list>
    </body>
</table>
<a href="/home">My page</a>
</@c.page>
