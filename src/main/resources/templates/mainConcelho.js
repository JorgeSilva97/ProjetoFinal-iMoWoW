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
  const concelhoId=document.getElementById("concelho").value;

  fetch("http://localhost:8080/concelho/historicos/preco/"+concelhoId,{}).then((response)=>
  {
    if(response.ok)
          return response.json()
      throw new Error("erro");
  }).then((concelho)=>{

    document.getElementById("precoVenda").value = concelho.precoMedioVenda
    document.getElementById("precoArrandamento").value = concelho.precoMedioArrendamento

    const historicosVSelect=document.getElementById("historicoVenda");
    const historicosVOptions=concelho.historicoVendas.map((historicov)=>
    {
      let opcao = document.createElement("option");
      opcao.value=historicov.id;
      opcao.text=historicov.precoAntigo;
      return opcao
    });
    historicosVOptions.forEach((opcao)=>
    {
      historicosVSelect.appendChild(opcao);
    })

    const historicosASelect=document.getElementById("historicoArrendamento");
    const historicosAOptions=concelho.historicoArrendamentos.map((historicoA)=>
    {
        let opc = document.createElement("option");
        opc.value=historicoA.id;
        opc.text=historicoA.precoAntigo;
        return opc;
    });
    historicosAOptions.forEach((opc)=>
    {
        historicosASelect.appendChild(opc);
    })
  }).catch(alert)
});