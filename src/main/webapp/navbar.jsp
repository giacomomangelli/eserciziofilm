<!-- navbar -->
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
       <span class="navbar-toggler-icon"></span>
     </button>

  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/home">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
        <div class="dropdown-menu" aria-labelledby="dropdown01">
          <a class="dropdown-item" href="${pageContext.request.contextPath}/home">Home</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/PrepareSearchRegistaServlet">Ricerca Registi</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/PrepareInsertRegistaServlet">Inserisci Regista</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/PrepareSearchFilmServlet">Ricerca Film</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/PrepareInsertFilmServlet">Inserisci Film</a>
        </div>
      </li>
    </ul>
   <ul class="nav navbar-nav navbar-right">
     <li><p class="navbar-text">Utente: ${userInfo.username }(${userInfo.nome } ${userInfo.cognome })
     <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></p> 
     </li>
   </ul>
  </div>
</nav>
