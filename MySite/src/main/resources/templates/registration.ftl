<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
Add new user


    <@l.login "/registration" true/>

<#--<div class="container mt-3">-->
<#--// если нет сообщений падений не произойдет-->
      <div class="form-group row">
          <a class="nav-link" href="/"> Back</a>
      </div>

    <div class="form-group row">
        <div class="col-sm-5">
            ${message?ifExists}
        <div />
    </div>
</@c.page>