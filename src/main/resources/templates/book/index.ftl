<#include "../bases/header.ftl"/>

	<div class="py-3">
		<a href="/book/create">Create</a>
	</div>
		<form method="post" action="search">
			<input type="text" class="form-control" name="inputName" placeholder="Name">
			<input type="number" class="form-control" name="inputnbPageMin" placeholder="Nombre de Page minimum">
			<input type="number" class="form-control" name="inputnbPageMax" placeholder="Nombre de Page maximum">
			<input type="text" class="form-control" name="inputPriceMin" placeholder="Price Maximum">
			<input type="text" class="form-control" name="inputPriceMax" placeholder="Price Minimum">
			<input type="submit" value="Submit">
		</form>
	<div>
	<#list items as item>
		<div>
		  	<h5>${item.name}</h5>
		    <p>Pages : ${item.nbPage}</p>
		    <p>Price : ${item.price}</p>
			<div >
				<a href="/book/details/${item.id}">Detailsd</a>
			</div>
		</div>
	</#list>
	</div>