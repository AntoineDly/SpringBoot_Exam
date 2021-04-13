<a href="/role/create">Create</a>
<#list items as item>
  <div>
    <div>
      ${item.name}
    </div>
    <div>
      <a href="/role/details/${item.id}">Details</a>
    </div>
  </div>
</#list>