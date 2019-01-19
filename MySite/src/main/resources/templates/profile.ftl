<#import "parts/common.ftl" as c>

<@c.page>
<div class="form-group row">
    <div class="col-sm-6">
        <h6>${username?ifExists}, here you can change your password</h6>
    </div>
</div>
    <form method="post" action="/user/profile">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Correct password:</label>
        <div class="col-sm-6">
            <input type="password" name="passuser" class="form-control" placeholder="Enter your password" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">New password:</label>
        <div class="col-sm-6">
            <input type="password" name="pass1" class="form-control" placeholder="Enter new password" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Confirm password:</label>
        <div class="col-sm-6">
            <input type="password" name="pass2" class="form-control" placeholder="Confirm new password" />
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Save</button>

    <div class="form-group row">
    <a class="nav-link" href="/home"> Next</a>
    </div>

    <div class="form-group row">
        <div class="col-sm-6">
        ${message?ifExists}
        <div />
    </div>

</form>
</@c.page>