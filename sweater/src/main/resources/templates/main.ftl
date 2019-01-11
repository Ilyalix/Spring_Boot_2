<#import "parts/common.ftl" as c>

<@c.page>
<#--<div>-->
    <#--<spam><a href = "/user">List of users</a> </spam>-->
<#--</div>-->

<#--<br>Найти:</br>-->
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/home" class="form-inline">
                <div class="form-group">
                    <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by Tag">
                </div >
                <button type="submit" class="btn btn-primary ml-2" >Search</button>
            </form>
        </div>
    </div>

<#--<br>Добавить:</br>-->
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="post" action="home" class="form-inline">
            <div class="form-group ">
                <input type="text" class="form-control" name="text" placeholder="Add new message"/>
            </div>
            <div class="form-group">
                <input type="text" class="form-control  ml-2 " name="tag" placeholder="Tag"/>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary ml-2" >Add</button>
            </div>
        </form>
    </div>
</div>

<#--<br>Удалить:</br>-->
<div class="form-row">
    <div class="form-group col-md-6">
    <form method="post" action="delete" class="form-inline">
        <input type="text" name="delete" class="form-control" placeholder="Delete by Tag">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary ml-2" >Delete</button>
    </form>
    </div>
</div>

<br>List of messages:</br>

<div class="card-columns">
<#list messages as mess>

<div class="card my-2">
    <#--<div class="card m-2" >-->
        <#--<b>${mess.id}</b>-->
    <#--</div>-->
    <div class="m-1" >
        <span> Text: ${mess.text}</span>
    </div>
     <div class="m-1" >
      <span> Tag: ${mess.tag}</span>
     </div>
    <div class="m-1" >
        <i>Author:</i><strong> ${mess.authorName}</strong>
    </div>
</div>

<#else>
No messages
</#list>
</div>

</@c.page>
