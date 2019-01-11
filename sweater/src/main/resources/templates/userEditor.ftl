<#import "parts/common.ftl" as c>
<@c.page>
User editor
 <form  method="post" action="/user">
     <input type="text" name="username" value="${user.username}">  <#--только 1 пользователь! -->
     <#list roles as role>  <#-- возможные роли -->
    <div>
        <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
 <#--   вывести название роли - ${role}, чтобы понимать активна или нет выводим ${user.roles?seq_contains(role)},
        этот метод возвращает булево значение и мы его преобразуем в стринг - )?string("checked", "") , "checked", то есть помеченая-->
</div>
</#list>
<input type="hidden" value="${user.id}" name="userId">
<input type="hidden" value="${_csrf.token}" name="_csrf">
<button type="submit">Save</button>
</form>

<a href="/home">My page</a>
</@c.page>