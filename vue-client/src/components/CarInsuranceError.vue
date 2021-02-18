<template>
  <div class="car-insurance-error">
    <div>
      <div class="error"></div>
    </div>
    <h2>Oida, her gikk noe galt</h2>

    <SecondaryButton text="Prøv igjen!" @click="$emit('reset')"></SecondaryButton>
  </div>
</template>

<script>
import SecondaryButton from "@/components/common/SecondaryButton";
export default {
  name: "CarInsuranceError",
  components: {SecondaryButton}
}
</script>

<style scoped>
.car-insurance-error {
  text-align: center;
  margin: 40px 0;
}

div:first-child {
  display: flex;
  justify-content: center;
}

.error {
  width: 150px;
  height: 150px;
  border-radius: 50%;
  position: relative;
  margin: 40px auto;
  box-shadow: 0 0 0 10px red;
}

.error:before, .error:after{
  content: '';
  position:absolute;
  width: 20px;
  height: 100px;
  left: 64px;
  top: 25px;
  background: red;
}
.error:before{
  transform: skew(28deg)
}
.error:after{
  transform: skew(-28deg);
}

</style>
