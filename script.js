<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calculadora de Impactos da Agricultura Familiar</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Inter', sans-serif;
        }
    </style>
</head>
<body class="bg-green-100 p-8">
    <h1 class="text-3xl font-semibold text-green-700 text-center mb-6">Calculadora de Impactos da Agricultura Familiar</h1>

    <div class="bg-white rounded-lg shadow-md p-6 mb-6">
        <h2 class="text-xl font-semibold text-green-600 mb-4">Entrada de Dados</h2>
        <form id="calculadora-form" class="space-y-4">
            <div>
                <label for="area-plantada" class="block text-gray-700 text-sm font-bold mb-2">Área Plantada (hectares):</label>
                <input type="number" id="area-plantada" name="areaPlantada" required class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                <div id="area-plantada-feedback" class="mt-1 text-red-500 text-sm"></div>
            </div>

            <div>
                <label for="tipo-cultura" class="block text-gray-700 text-sm font-bold mb-2">Tipo de Cultura:</label>
                <select id="tipo-cultura" name="tipoCultura" required class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                    <option value="" disabled selected>Selecione a cultura</option>
                    <option value="milho">Milho</option>
                    <option value="feijao">Feijão</option>
                    <option value="arroz">Arroz</option>
                    <option value="soja">Soja</option>
                    <option value="outros">Outros</option>
                </select>
                <div id="tipo-cultura-feedback" class="mt-1 text-red-500 text-sm"></div>
            </div>

            <div>
                <label for="producao-total" class="block text-gray-700 text-sm font-bold mb-2">Produção Total (kg):</label>
                <input type="number" id="producao-total" name="producaoTotal" required class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                <div id="producao-total-feedback" class="mt-1 text-red-500 text-sm"></div>
            </div>

            <div>
                <label for="custo-producao" class="block text-gray-700 text-sm font-bold mb-2">Custo de Produção (R$):</label>
                <input type="number" id="custo-producao" name="custoProducao" required class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                 <div id="custo-producao-feedback" class="mt-1 text-red-500 text-sm"></div>
            </div>

            <button type="submit" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Calcular Impactos</button>
        </form>
    </div>

    <div id="resultados" class="bg-white rounded-lg shadow-md p-6" style="display: none;">
        <h2 class="text-xl font-semibold text-green-600 mb-4">Resultados</h2>
        <div id="producao-alimentos" class="mb-4">
            <h3 class="text-lg font-semibold text-gray-800">Produção de Alimentos:</h3>
            <p id="producao-alimentos-texto" class="text-gray-700"></p>
        </div>
        <div id="impacto-economico" class="mb-4">
            <h3 class="text-lg font-semibold text-gray-800">Impacto Econômico:</h3>
            <p id="impacto-economico-texto" class="text-gray-700"></p>
        </div>
        <div id="impacto-social" class="mb-4">
            <h3 class="text-lg font-semibold text-gray-800">Impacto Social:</h3>
            <p id="impacto-social-texto" class="text-gray-700"></p>
        </div>
        <div id="impacto-ambiental" class="mb-4">
            <h3 class="text-lg font-semibold text-gray-800">Impacto Ambiental:</h3>
            <p id="impacto-ambiental-texto" class="text-gray-700"></p>
        </div>
    </div>

    <script>
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
