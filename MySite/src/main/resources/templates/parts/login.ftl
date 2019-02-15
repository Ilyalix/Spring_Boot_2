<#macro login path isRegisterForm> <#--  макрос login имеет переменую path -->
<#--отобраать или нет ссылку регистрации определяем с помощью isRegisterForm-->

<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">User Name:</label>
        <div class="col-sm-6">
            <input type="text" name="username" class="form-control" placeholder="User name" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password:</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Password" />
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <#if isRegisterForm>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Repeat password:</label>
        <div class="col-sm-6">
            <input type="password" name="password2" class="form-control" placeholder="Password" />
        </div>
    </div>
    </#if>
    <#--показываем эту ссылку если это НЕ форма регистрации-->
    <#if !isRegisterForm><a href="/registration">Sign up</a></#if>
    <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>

    <#if !isRegisterForm>
        <div class="form-group row mt-3">
        <div class="col-sm-5">
           <p class="text-success">
               ${mess?ifExists}
           </p>
        <div />
    <div />
    </#if>

</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Sign out</button>
</form>
</#macro>