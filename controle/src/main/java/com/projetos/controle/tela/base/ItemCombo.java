package com.projetos.controle.tela.base;
public class ItemCombo<T> {
		private String label;
		private T valor;
		
		public ItemCombo(String label, T valor) {
			this.label = label;
			this.valor = valor;
		}
		
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public T getValor() {
			return valor;
		}
		public void setValor(T valor) {
			this.valor = valor;
		}
		@Override
		public String toString() {
			return label;
		}
	}