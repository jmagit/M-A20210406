import { Attribute, Directive, ElementRef, forwardRef } from '@angular/core';
import { ValidatorFn, AbstractControl, NG_VALIDATORS, Validator } from '@angular/forms';

@Directive({
  selector: '[lowercase][formControlName],[lowercase][formControl],[lowercase][ngModel]',
  providers: [{ provide: NG_VALIDATORS, useExisting: LowerCaseValidatorDirective, multi: true }]
})
export class LowerCaseValidatorDirective implements Validator {
  validate(control: AbstractControl): { [key: string]: any } {
    if (!control.value) { return null; }
    return control.value === control.value.toLowerCase() ? null : { lowercase: 'No esta en minúsculas' }
  }
}

@Directive({
  // tslint:disable-next-line:directive-selector
  selector: 'input[formControlName],input[formControl],input[ngModel]',
  providers: [
      { provide: NG_VALIDATORS, useExisting: forwardRef(() => HTML5ValidatorDirective), multi: true }
  ]
})
export class HTML5ValidatorDirective implements Validator {
  constructor(private elem: ElementRef) { }
  validate(control: AbstractControl): { [key: string]: any } {
      const valor = control.value;
      if (valor) {
        const dom = this.elem.nativeElement;
        if (dom.validity && !dom.validity.valid) { // dom.checkValidity();
          let err = { html5: dom.validationMessage }
          if(dom.validity.typeMismatch) err['type'] = dom.validationMessage;
          if(dom.validity.rangeUnderflow) {
            const valor = dom.min;
            err['min'] = `Debe tener un mínimo de ${valor === 1 ? 'un carácter' : valor + 'carácteres' } `;
          }
          if(dom.validity.rangeOverflow) {
            const valor = dom.max;
            err['max'] = `Debe tener un máximo de ${valor === 1 ? 'un carácter' : valor + 'carácteres' } `;
          }
          if(dom.validity.stepMismatch ) err['step'] = dom.validationMessage;
          return err;
        }
      }
      return null;
  }
}

export function NIFValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } => {
      if (!control.value) { return null; }
      const err = { nif: { invalidFormat: true, invalidChar: true } };
      if (/^\d{1,8}\w$/.test(control.value)) {
          const letterValue = control.value.substr(control.value.length - 1);
          const numberValue = control.value.substr(0, control.value.length - 1);
          err.nif.invalidFormat = false;
          return letterValue.toUpperCase() === 'TRWAGMYFPDXBNJZSQVHLCKE'.charAt(numberValue % 23) ? null : err;
      } else { return err; }
  };
}
@Directive({
  selector: '[nif][formControlName],[nif][formControl],[nif][ngModel]',
  providers: [{ provide: NG_VALIDATORS, useExisting: NIFValidatorDirective, multi: true }]
})
export class NIFValidatorDirective implements Validator {
  validate(control: AbstractControl): { [key: string]: any } {
      return NIFValidator()(control);
  }
}

@Directive({
  selector: '[validateEqual][formControlName],[validateEqual][formControl],[validateEqual][ngModel]',
  providers: [ { provide: NG_VALIDATORS, useExisting: forwardRef(() => EqualValidatorDirective), multi: true } ]
})
export class EqualValidatorDirective implements Validator {
  constructor( @Attribute('validateEqual') public validateEqual: string) {}
  validate(control: AbstractControl): { [key: string]: any } | null {
      let valor = control.value;
      let cntrlBind = control.root.get(this.validateEqual);

      if (valor) {
        return (!cntrlBind || valor !== cntrlBind.value) ? { 'validateEqual': `${valor} <> ${cntrlBind.value}` } : null;
      }
      return null;
  }
}

export const MIS_VALIDADORES = [ LowerCaseValidatorDirective, HTML5ValidatorDirective, NIFValidatorDirective, EqualValidatorDirective ];
