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
      <a href="/user/details/${item.id}">Details</a>
    </div>
  </div>
</#list>