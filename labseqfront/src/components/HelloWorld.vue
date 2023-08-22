<template>
  <div class="hello">
    <h1>Calculando Função de L(n)</h1>
    <input v-model="searchParameter" placeholder="Insira o parâmetro de busca" />
    <button @click="fetchData">Fazer Requisição</button>
    <div v-if="data">
      <p>O valor calculado para {{ parameter }} na função de L(n) é :</p>
      <pre>{{ data }}</pre>
    </div>
  </div>
</template>
<script>
import axios from "axios"; 

export default {
  data() {
    return {
      searchParameter: "", 
      data: null,
    };
  },
  methods: {
    fetchData() {
      const parameter = this.searchParameter;
      this.data = "";
      const url = `http://localhost:8080/labseq/${parameter}`;

      axios.get(url)
        .then((response) => {
          this.data = response.data;
        })
        .catch((error) => {
          console.error("Erro ao fazer requisição:", error);
        });
    },
  },
};
</script>

<style scoped>

</style>