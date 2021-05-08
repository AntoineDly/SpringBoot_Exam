<#include "../bases/header.ftl"/>
<a href="/user/create">Create</a>
<#list items as item>
  <div>
    <div>
      ${item.firstname}
    </div>
    <div>
      ${item.lastname}
    </div>
    <div>
      ${item.role.name}
    </div>
    <div>
      <a href="/user/details/${item.id}">Details</a>
      <a href="/user/connect/${item.id}">Connect</a>
    </div>
    <span>
  </div>
</#list>