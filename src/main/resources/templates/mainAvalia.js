
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

//ao carregar no "para venda", cria imóvel, cria venda e retorna a valor do negócio e avaliação
let venda=document.getElementById("venda");
      venda.addEventListener("click",()=>
      {
        const venda = 
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
          preco:parseFloat(document.getElementById("precoPedido").value)
        }
  
        fetch("http://localhost:8080/venda",
        {
          method:"post",
          body:JSON.stringify(venda),
          headers:{"Content-Type":"application/json"}
        }).then(response=>
          {
              if(response.ok)
                  return response.json();
              throw new Error("Could not create a new imovel");   
        }).then((json)=>{
            document.getElementById("avaliacaoEuros").value = json.valorAvaliacao;
            document.getElementById("avaliacao").value = json.avaliacao;
        }).catch(alert);
      });

//ao carregar no "para arrendamento", cria imóvel, cria arrendamento e retorna a valor do negócio e avaliação
let arrendamento=document.getElementById("arrendamento");
    arrendamento.addEventListener("click",()=>
      {
        const arrendamento = 
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
          preco:parseFloat(document.getElementById("precoPedido").value)
        }
        fetch("http://localhost:8080/arrendamento",
        {
          method:"post",
          body:JSON.stringify(arrendamento),
          headers:{"Content-Type":"application/json"}
        }).then(response=>
          {
            if(response.ok)
                  return response.json();
              throw new Error("Could not create a new arrendamento");
          }).then((json)=>{
            document.getElementById("avaliacaoEuros").value = json.valorAvaliacaoArr;
            document.getElementById("avaliacao").value = json.avaliacaoArr;
          }).catch(alert)
      });
      