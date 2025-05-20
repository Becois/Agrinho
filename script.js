        const form = document.getElementById('calculadora-form');
        const resultadosDiv = document.getElementById('resultados');

        form.addEventListener('submit', function(event) {
            event.preventDefault();

            let areaPlantada = document.getElementById('area-plantada').value;
            let tipoCultura = document.getElementById('tipo-cultura').value;
            let producaoTotal = document.getElementById('producao-total').value;
            let custoProducao = document.getElementById('custo-producao').value;

            let hasErrors = false;

            if (!areaPlantada) {
                form.querySelector('#area-plantada-feedback').textContent = 'Por favor, insira a área plantada.';
                hasErrors = true;
            } else {
                form.querySelector('#area-plantada-feedback').textContent = '';
            }
             if (!producaoTotal) {
                form.querySelector('#producao-total-feedback').textContent = 'Por favor, insira a produção total.';
                hasErrors = true;
            } else {
                form.querySelector('#producao-total-feedback').textContent = '';
            }
            if (!custoProducao) {
                form.querySelector('#custo-producao-feedback').textContent = 'Por favor, insira o custo de produção.';
                hasErrors = true;
            } else {
                form.querySelector('#custo-producao-feedback').textContent = '';
            }

            if (!tipoCultura) {
                form.querySelector('#tipo-cultura-feedback').textContent = 'Por favor, selecione o tipo de cultura.';
                hasErrors = true;
            } else {
                form.querySelector('#tipo-cultura-feedback').textContent = '';
            }


            if (hasErrors) {
                return;
            }
            areaPlantada = parseFloat(areaPlantada);
            producaoTotal = parseFloat(producaoTotal);
            custoProducao = parseFloat(custoProducao);


            let producaoAlimentosTexto = `A produção total de ${tipoCultura} é de ${producaoTotal} kg em uma área de ${areaPlantada} hectares.`;
            let impactoEconomicoTexto = `O custo de produção é de R$ ${custoProducao}. O valor da produção será calculado com base no tipo de cultura e produção.`;
            let impactoSocialTexto = "A agricultura familiar contribui para a geração de empregos e renda na comunidade.";
            let impactoAmbientalTexto = "O impacto ambiental varia dependendo das práticas agrícolas utilizadas.  Práticas sustentáveis podem minimizar os impactos negativos.";

            if (tipoCultura === "milho") {
                impactoEconomicoTexto += " Estimamos um valor de produção de R$ " + (producaoTotal * 2.5).toFixed(2) + ".";
            } else if (tipoCultura === "feijao") {
                 impactoEconomicoTexto += " Estimamos um valor de produção de R$ " + (producaoTotal * 3).toFixed(2) + ".";
            }
             else if (tipoCultura === "arroz") {
                 impactoEconomicoTexto += " Estimamos um valor de produção de R$ " + (producaoTotal * 2).toFixed(2) + ".";
            }
            else if (tipoCultura === "soja") {
                 impactoEconomicoTexto += " Estimamos um valor de produção de R$ " + (producaoTotal * 4).toFixed(2) + ".";
            }
            else{
                impactoEconomicoTexto += " Estimamos um valor de produção de R$ " + (producaoTotal * 2.8).toFixed(2) + ".";
            }


            document.getElementById('producao-alimentos-texto').textContent = producaoAlimentosTexto;
            document.getElementById('impacto-economico-texto').textContent = impactoEconomicoTexto;
            document.getElementById('impacto-social-texto').textContent = impactoSocialTexto;
            document.getElementById('impacto-ambiental-texto').textContent = impactoAmbientalTexto;

            resultadosDiv.style.display = 'block';
            window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });
        });
    </script>
</body>
</html>
