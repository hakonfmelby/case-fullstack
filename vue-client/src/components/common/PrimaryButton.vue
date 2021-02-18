<template>
  <button
      @click="$emit('click')"
  >
    {{ text }}
  </button>

</template>

<script>
export default {
  name: "PrimaryButton",
  props: {
    text: {
      required: true,
      type: String,
    }
  }
}
</script>

<style scoped lang="scss">
button {
  border-radius: 28px;
  background-color: #000000;
  color: #FFFFFF;
  font-size: 24px;
  padding: 12px 24px;
  cursor: pointer;
  margin: 6px;
}

button:hover {
  background-color: lighten(#000000, 5%);
}
</style>
