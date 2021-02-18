<template>
  <div class="car-insurance-form">
    <InputField
        label="Bilens registeringsnummer"
        placeholder="E.g. AB 12345"
        v-model="regNumber"
        :error="regNumberError"
        name="regNumberField"
    ></InputField>

    <SelectField
        label="Din bonus"
        v-model="bonus"
        name="bonusField"
        :options="['10%', '20%', '30%', '40%', '50%', '60%', '70%', '80%', '90%']"
    ></SelectField>

    <InputField
        :max-length="11"
        label="Fødelsnummer"
        type="number"
        placeholder="11 siffer"
        v-model="birthNumber"
        :error="birthNumberError"
        name="birthNumberField"
    ></InputField>

    <div class="row">
      <InputField
          label="Fornavn"
          v-model="firstName"
          :error="firstNameError"
          name="firstNameField"
      ></InputField>

      <InputField
          label="Etternavn"
          v-model="lastName"
          :error="lastNameError"
          name="lastNameField"
      ></InputField>
    </div>

    <InputField
        type="email"
        label="E-post"
        v-model="email"
        :error="emailError"
        name="emailField"
    ></InputField>

    <div class="row">
      <PrimaryButton
          text="Kjøp"
          @click="submit"
      ></PrimaryButton>
      <SecondaryButton
          text="Avbryt"
          @click="cancel"
      ></SecondaryButton>
    </div>
  </div>
</template>

<script>
import InputField from "@/components/common/InputField";
import PrimaryButton from "@/components/common/PrimaryButton";
import SecondaryButton from "@/components/common/SecondaryButton";
import SelectField from "@/components/common/SelectField";

let timeout = null;

export default {
  name: "CarInsuranceForm",
  components: {SelectField, SecondaryButton, PrimaryButton, InputField},
  data: () => ({
    regNumber: null,
    regNumberError: null,
    bonus: '10%',
    birthNumber: null,
    birthNumberError: null,
    firstName: null,
    firstNameError: null,
    lastName: null,
    lastNameError: null,
    email: null,
    emailError: null,
  }),
  beforeDestroy() {
    clearTimeout(timeout);
  },
  watch: {
    regNumber() {
      this.regNumberError = null;
    },
    birthNumber() {
      this.birthNumberError = null;
    },
    firstName() {
      this.firstNameError = null;
    },
    lastName() {
      this.lastNameError = null;
    },
    email() {
      this.emailError = null;
    }
  },
  methods: {
    validateEmail(email) {
      const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(String(email).toLowerCase());
    },
    validateRegNumber(reg) {
      if (!reg) {
        return false;
      }

      // add space if user forgot
      if (!reg.includes(' ')) {
        if (reg.length !== 7) {
          return false;
        }

        reg = reg.slice(0, 2) + " " + reg.slice(2, 7);
      }

      const split = reg.split(' ');

      return !(split.length !== 2 || isNaN(split[1]) || split[1].length !== 5 || split[0].length !== 2 ||
          !split[0].match(/[a-z]/i));
    },
    validate() {
      this.resetErrors();

      let valid = true;

      if (typeof this.birthNumber !== 'string' || this.birthNumber.length !== 11) {
        valid = false;
        this.birthNumberError = 'Fødselsnummer må være 11 sifre, uten mellomrom';
      }

      if (!this.lastName) {
        valid = false;
        this.lastNameError = 'Etternavn må fylles inn';
      }

      if (!this.firstName) {
        valid = false;
        this.firstNameError = 'Fornavn må fylles inn';
      }

      if (!this.validateEmail(this.email)) {
        valid = false;
        this.emailError = 'Ugyldig E-post';
      }

      if (!this.validateRegNumber(this.regNumber)) {
        valid = false;
        this.regNumberError = 'Ugyldig registreringsnummer. Format: AB 12345';
      }

      return valid;
    },
    async submit() {
      if (this.validate()) {
        this.$emit('submit', {
          regNumber: this.regNumber,
          email: this.email,
          lastName: this.lastName,
          firstName: this.firstName,
          birthNumber: this.birthNumber,
          bonus: this.bonus,
        });
      } else {
        timeout = setTimeout(() => {
          document.getElementsByClassName('error')[0].focus();
        }, 500);
      }
    },
    cancel() {
      this.regNumber = null;
      this.email = null;
      this.lastName = null;
      this.firstName = null;
      this.birthNumber = null;
      this.bonus = '10%';
      this.resetErrors();
    },
    resetErrors() {
      this.regNumberError = null;
      this.emailError = null;
      this.lastNameError = null;
      this.firstNameError = null;
      this.birthNumberError = null;
    }
  }
}
</script>

<style scoped>
.car-insurance-form {
  margin-top: 32px;
}

.row {
  display: flex;
  flex-wrap: wrap;
}

@media only screen and (max-width: 700px) {
  .row {
    display: block;
  }
}
</style>
