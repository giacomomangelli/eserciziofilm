<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Modifica Film</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Modifica Film</h5> 
		    </div>
		    <div class='card-body'>

					<form method="post" action="ExecuteUpdateFilmServlet" novalidate="novalidate" >
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Titolo</label>
								<input type="text" name="titolo" id="titolo" class="form-control" placeholder="Inserire il titolo" value="${update_film_attr.titolo }">
							</div>
							
							<div class="form-group col-md-6">
								<label>Genere</label>
								<input type="text" name="genere" id="genere" class="form-control" placeholder="Inserire il genere" value="${update_film_attr.genere }">
							</div>
						</div>
						
						<div class="form-row">	
							<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${update_film_attr.dataPubblicazione}' />
							<div class="form-group col-md-6">
								<label>Data di Pubblicazione</label>
                        		<input class="form-control" id="dataPubblicazione" type="date" placeholder="dd/MM/yy"
                            		title="formato : gg/mm/aaaa"  name="dataPubblicazione" value="${parsedDate}" >
							</div>
							<div class="form-group col-md-6">
								<label>Durata (minuti)</label>
								<input type="number" class="form-control" name="minutiDurata" id="minutiDurata" placeholder="Inserire la durata" value="${update_film_attr.minutiDurata }">
							</div>
						</div>
						<div class="form-row">	
							<div class="form-group col-md-6">
								<label for="regista.id">Regista</label>
							    <select class="form-control" id="regista.id" name="regista.id">
							    	<option value="" selected> -- Selezionare una voce -- </option>
							      	<c:forEach items="${registi_list_attribute }" var="registaItem">
							      		<option value="${registaItem.id}" ${update_film_attr.regista.id == registaItem.id?'selected':''} >${registaItem.nome } ${registaItem.cognome }</option>
							      	</c:forEach>
							    </select>
							</div>
						</div>
							
						<div class='card-footer'>
							 <a href="ExecuteListFilmServlet" class='btn btn-outline-secondary' style='width:80px'>
				             	<i class='fa fa-chevron-left'></i> Back
				       		 </a>
				       		 <input type = "hidden" name = "idToUpdate" value = ${update_film_attr.id } />
							<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Modifica</button>
						</div>						
					</form>

		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>