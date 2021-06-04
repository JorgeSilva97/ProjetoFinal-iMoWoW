
// colaca concelhos nos select
document.addEventListener("DOMContentLoaded",(event)=>{
  fetch("http://localhost:8080/concelho",{}).then((response)=>
  {
      if(response.ok)
          return response.json()
      throw new Error("erro");
  }).then((concelhos)=>
  {
      const concelhosSelect=document.getElementById("concelho");
      const concelhosOptions=concelhos.map((concelho)=>
      {
          let option=document.createElement("option");
          option.value=concelho.id;
          option.text=concelho.nome;
          return option;
      });
      concelhosOptions.forEach((option)=>
      {
          concelhosSelect.appendChild(option);
      })
  }).catch(alert)
});

//ao carregar "consultar" insere informação sobre preco e seu histórico
let informacao = document.getElementById("consultar");
informacao.addEventListener("click", ()=>
{
  fetch("http://localhost:8080/concelho/historicos/preco/",{}).then((response)=>
  {
    if(response.ok)
          return response.json()
      throw new Error("erro");
  }).then((historicos)=>{
    const historicosSelect=document.getElementById("historivoVenda");
    const historicosOptions=historicos.map((historico)=>
    {
      let opcao = document.createElement("option");
      opcao.value=historico.id;
      opcao.text=historico.nome;
    });
    historicosOptions.forEach((opcao)=>
    {
      historicosSelect.appendChild(opcao);
    })
  }).catch(alert)
});

//redirecinar para a pagina de concelho
let consultaConcelhos=document.getElementById("consultaConcelhosPage");
consultaConcelhos.addEventListener("click",()=>
{
  window.location.assign("./concelhos.html")

});

//ao carregar no "para venda", cria imóvel, cria venda e retorna a valor do negócio e avaliação
let venda=document.getElementById("venda");
      venda.addEventListener("click",()=>
      {
        const imovel = 
        {
          //imovelId ???
          userId:parseInt(document.getElementById("utilizador").value),
          concelhoId:parseInt(document.getElementById("concelho").value),
          metrosQuadrados:parseFloat(document.getElementById("metros").value),
          anoConstrucao:parseInt(document.getElementById("ano").value),
          estadoImovel:parseInt(document.getElementById("estado").value),
          topologia:parseInt(document.getElementById("topologia").value),
          piscina:document.getElementById("piscina").value==="0"?false:true,
          jardim:document.getElementById("jardim").value==="0"?false:true,
          garagem:document.getElementById("garagem").value==="0"?false:true,
          elevador:document.getElementById("elevador").value==="0"?false:true,
        }
        const venda = {preco:parseFloat(document.getElementById("precoPedido").value)}

        fetch("http://localhost:8080/venda",
        {
          method:"post",
          body:JSON.stringify(imovel, venda),
          headers:{"Content-Type":"application/json"}
        }).then(response=>
          {
              if(response.ok)
                  return response.json();
              throw new Error("Could not create a new imovel");   
        }).then((json)=>{
          //
        }).catch(alert);
      });

//ao carregar no "para arrendamento", cria imóvel, cria arrendamento e retorna a valor do negócio e avaliação
let arrendamento=document.getElementById("arrendamento");
    arrendamento.addEventListener("click",()=>
      {
        const arrendamento = {preco:parseFloat(document.getElementById("precoPedido").value)}

        const imovel = 
        {
          userId:parseInt(document.getElementById("utilizador").value),
          concelhoId:parseInt(document.getElementById("concelho").value),
          metrosQuadrados:parseFloat(document.getElementById("metros").value),
          anoConstrucao:parseInt(document.getElementById("ano").value),
          estadoImovel:parseInt(document.getElementById("estado").value),
          topologia:parseInt(document.getElementById("topologia").value),
          piscina:document.getElementById("piscina").value==="0"?false:true,
          jardim:document.getElementById("jardim").value==="0"?false:true,
          garagem:document.getElementById("garagem").value==="0"?false:true,
          elevador:document.getElementById("elevador").value==="0"?false:true,
        }
        fetch("http://localhost:8080/arrendamento",
        {
          method:"post",
          body:JSON.stringify(imovel, arrendamento),
          headers:{"Content-Type":"application/json"}
        }).then(response=>{
          if(response.ok){
            return response.json();
          }
          throw new Error("Could not create a new imovel");            
        }).then((json)=>{
          //
        })catch(alert);
      });