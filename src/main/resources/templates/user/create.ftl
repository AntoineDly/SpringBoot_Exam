<#include "../bases/header.ftl"/>
<#if errors??>
${errors}
</#if>
<#if moncookie??>
${moncookie}
</#if>
<form method="POST">
  <label for="firstname">Firstname</label>
  <input type="text" id="firstname" name="firstname"/>
  <label for="lastname">Lastname</label>
  <input type="text" id="lastname" name="lastname"/>

  <#include "./rolesSelection.ftl">

  <input type="submit" value="Create"/>
</form>