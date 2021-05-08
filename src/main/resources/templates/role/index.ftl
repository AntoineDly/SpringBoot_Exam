<#include "../bases/header.ftl"/>
<a href="/role/create">Create</a>
<#list items as item>
  <div>
    <div>Name : ${item.name}</div>
    <div>
      <a href="/role/details/${item.id}">Details</a>
    </div>
  </div>
</#list>