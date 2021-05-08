<#include "../bases/header.ftl"/>
<#list items as item>
	<div> Name : ${item.name} </div>
	<div> NbPage : ${item.nbPage} </div>
	<div> Price : ${item.price}	</div>
</#list>