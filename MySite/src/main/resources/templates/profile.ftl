<#import "parts/common.ftl" as c>

<@c.page>
<h5>${username?ifExists}</h5>
<form method="post" action="/user/profile">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password:</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Password" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password:</label>
        <div class="col-sm-6">
            <input type="password" name="password2" class="form-control" placeholder="Confirm password" />
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Save</button>

    <div class="form-group row">
    <a class="nav-link" href="/home"> Next</a>
    </div>

    <div class="form-group row">
        <div class="col-sm-3">
        ${message?ifExists}
        <div />
    </div>

</form>
</@c.page>