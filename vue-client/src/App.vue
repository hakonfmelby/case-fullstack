<template>
  <div id="app">
    <section>
      <h1>Kjøp Bilforsikring</h1>

      <p>
        Det er fire forskjellige forsikringer å velge mellom. Ansvarsforsikring er lovpålagt om kjøretøyet er
        registrert og skal brukes på veien. I tillegg kan du utvide forsikringen avhengig av hvor gammel
        bilen din er og hvordan du bruker den.
      </p>

      <CarInsuranceProcessing v-if="processing"></CarInsuranceProcessing>
      <CarInsuranceError v-else-if="error" @reset="error = false"></CarInsuranceError>
      <CarInsuranceComplete
          v-else-if="complete"
          @reset="complete = false"
          :new-insurance="newInsurance"
      ></CarInsuranceComplete>
      <CarInsuranceForm v-else @submit="submitCarInsurance"></CarInsuranceForm>
    </section>
  </div>
</template>

<script>
import CarInsuranceForm from "@/components/CarInsuranceForm";
import {put} from "@/lib/api";
import CarInsuranceComplete from "@/components/CarInsuranceComplete";
import CarInsuranceProcessing from "@/components/CarInsuranceProcessing";
import CarInsuranceError from "@/components/CarInsuranceError";

let timeout = null;

export default {
  name: 'App',
  components: {
    CarInsuranceError,
    CarInsuranceProcessing,
    CarInsuranceComplete,
    CarInsuranceForm
  },
  data: () => ({
    processing: false,
    complete: false,
    newInsurance: null,
    error: true,
  }),
  methods: {
    async submitCarInsurance(data) {
      this.processing = true;

      const response = await put(data);

      if (response.status !== 201) {
        this.error = true;
      } else {
        this.complete = true;
        this.newInsurance = response.data;
      }

      // Fake 1 second load to show animation
      timeout = setTimeout(() => {
        this.processing = false;
      }, 1000);
    }
  },
  beforeDestroy() {
    clearTimeout(timeout);
  }
}
</script>

<style lang="scss">
$font-dir: "/fonts/";

@font-face {
  font-family: "Profilfont-Regular-Web";
  src: url("#{$font-dir}/Profilfont-Regular-Web.woff") format("woff"),
  url("#{$font-dir}/Profilfont-Regular-Web.woff2") format("woff2");
  font-style: normal;
  font-weight: normal;
}

#app {
  font-family: Profilfont-Regular-Web, serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: black;
}

section {
  max-width: 600px;
  margin: auto;
  padding: 6px 24px;
}

h1 {
  font-size: 32px;
}

p {
  font-size: 18px;
}
</style>
