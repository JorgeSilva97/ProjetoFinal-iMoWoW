
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

        fetch("http://localhost:8080/imovel",
        {
          method:"post",
          body:JSON.stringify(imovel),
          headers:{"Content-Type":"application/json"}
        }).then(response=>
          {
              if(response.ok)
                  return response.json();
              throw new Error("Could not create a new imovel");   
        }).then((json)=>
        {
              fetch("http://localhost:8080/venda", 
              {
                method:"post",
                body:JSON.stringify({imovelId:json.id,preco:document.getElementById("precoPedido")}),
                headers:{"Content-Type":"application/json"}
              }).then(response=>
                  {
                      if(response.ok)
                          return response.json();
                      throw new Error("Could not create a new venda");
              }).then((json)=>{
                    fetch("http://localhost:8080/venda/valor",
                    {
                      method:"get",
                      body:JSON.stringify({vendaId:json.id}),
                      headers:{"Content-Type":"application/json"}
                    }).then(response=>
                      {
                        if(response.ok)
                          return response.json();
                      throw new Error("Could not insert a price on venda");
                    }).then((json)=>{
                      let valor = document.getElementById("avaliacaoEuros");
                      valor.value=JSON.stringify(json);
                    }).catch((err)=>alert(err));
                    
                  })
              }).catch(alert)
      });

      //ao carregar no "para arrendamento", cria imóvel, cria arrendamento e retorna a valor do negócio e avaliação
      let arrendamento=document.getElementById("arrendamento");
      arrendamento.addEventListener("click",()=>
      {
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
        fetch("http://localhost:8080/imovel",{
          method:"post",
          body:JSON.stringify(imovel),
          headers:{"Content-Type":"application/json"}
        }).then(response=>{
          if(response.ok){
            return response.json();
          }
          throw new Error("Could not create a new imovel");            
        }).then((json)=>{
          //
        })
      });