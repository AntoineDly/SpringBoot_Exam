<label for="firstname">Role</label>
<select name="roleId">
  <option selected disabled>No role</option>
  <#list roles as key, value>
    <option value="${key}">${value}</option>
  </#list>
</select>