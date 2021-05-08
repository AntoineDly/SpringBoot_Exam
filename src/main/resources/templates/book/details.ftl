 <#include "../bases/header.ftl"/>


	<div>
		<p>Information du livre : </p>
		<div>
		  <label>Name</label>
		  <label>${item.name}<label/>
		</div></br>

		<div>
		  <label>Price</label>
		  <label>${item.price}<label/>
		</div></br>
		
		<div>
		  <label>Nombre de page</label>
		  <label>${item.nbPage}<label/>
		</div></br>
	</div>
	<div>
		<p>Liste des Seller : </p>
	
		<#list users as user>

		<div>
			<h5 >${user.firstname} ${user.lastname}</h5>
			<p >Role : ${user.role.name}</p>
			<div>
				<a href="/book/buy/${item.id}/${user.id}">Buy</a>
			</div>
		</div>
	</#list>
	</div>