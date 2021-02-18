<template>
  <div class="car-insurance-complete" v-if="newInsurance">
    <div>
      <div class="checkmark"></div>
    </div>
    <h2>Bilforskring kjøpt!</h2>
    <p>
      <b>ID:</b> {{ newInsurance.id }}<br/>
      <b>Status:</b> {{ newInsurance.status }}
    </p>

    <SecondaryButton text="Kjøp en til!" @click="$emit('reset')"></SecondaryButton>
  </div>
</template>

<script>
import SecondaryButton from "@/components/common/SecondaryButton";

export default {
  name: "CarInsuranceComplete",
  components: {SecondaryButton},
  props: {
    newInsurance: {
      required: true,
      type: Object,
    }
  }
}
</script>

<style scoped>
.car-insurance-complete {
  text-align: center;
  margin: 40px 0;
}

div:first-child {
  display: flex;
  justify-content: center;
}

.checkmark {
  transform: rotate(45deg);
  height: 48px;
  width: 24px;
  border-bottom: 7px solid #78b13f;
  border-right: 7px solid #78b13f;
}
</style>
