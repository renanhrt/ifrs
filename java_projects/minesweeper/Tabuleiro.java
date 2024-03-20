package minesweepergame;

import java.util.Random;

public class Tabuleiro {

		protected int dimensoes;
		protected final int mina = 9;
		protected int[][] tabuleiro;
		protected int bombas;
		protected int campos;
		
		Random random = new Random();
		public void tabuleirar() {
			int contador = 0;
			int[][] tabula = new int[dimensoes][dimensoes];
			
			for (int linhas = 0; linhas < tabula.length; linhas++) {
				for (int colunas = 0; colunas < dimensoes; colunas++) {
					if (random.nextInt(7) == 1) {
						tabula[linhas][colunas] = mina;
						contador++;
					} else {
						tabula[linhas][colunas] = 0;
					}
				}
			}

			for (int linhas = 0; linhas < tabula.length; linhas++) {
				for (int colunas = 0; colunas < dimensoes; colunas++) {
					if(tabula[linhas][colunas] == 0) {
						int bombas = 0;
						for (int i = -1; i < 2; i++) {
							for (int j = -1; j < 2; j++) {
								try {
									if(tabula[linhas+i][colunas+j] == 9) {
										bombas++;
									}
								} catch (Exception e) {
									// TODO: handle exception
								}
							}
						}
						tabula[linhas][colunas] = bombas;
						}
					}
				}
			this.bombas = contador;
			this.campos = (dimensoes*dimensoes)-contador;
			this.tabuleiro = tabula;
		}
}