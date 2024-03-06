package minesweepergame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Minesweeper {

	private JFrame frame;
	Tabuleiro t = new Tabuleiro();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Minesweeper window = new Minesweeper();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Minesweeper() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setResizable(false);

		@SuppressWarnings("unused")
		Tabuleiro t = new Tabuleiro();

		frame.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_1, "name_248694201365500");
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Campo Minado");
		lblNewLabel.setBounds(100, 11, 394, 135);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Selecione a dificuldade:");
		lblNewLabel_1.setBounds(134, 122, 316, 64);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_1.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Fácil");
		btnNewButton.setBounds(38, 222, 147, 97);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = buttonate(10);
				frame.setBounds(100, 100, 500, 500);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(panel);
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(btnNewButton);

		JButton btnMdio = new JButton("Médio");
		btnMdio.setBounds(211, 222, 157, 97);
		btnMdio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = buttonate(12);
				frame.setBounds(100, 100, 650, 650);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(panel);
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
			}
		});
		btnMdio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(btnMdio);

		JButton btnDifcil = new JButton("Difícil");
		btnDifcil.setBounds(396, 222, 157, 97);
		btnDifcil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = buttonate(14);
				frame.setBounds(100, 100, 750, 750);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(panel);
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
			}
		});
		btnDifcil.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(btnDifcil);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(171, 330, 252, 219);
		ImageIcon icon = new ImageIcon(
				"C:\\Users\\renan.hardt\\Desktop\\Workspace\\TrabPratico\\src\\minesweepergame\\bomb.png");
		Image image = icon.getImage().getScaledInstance(230, 230, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(image);
		lblNewLabel_2.setIcon(scaledIcon);
		panel_1.add(lblNewLabel_2);

	}

	public JPanel buttonate(int dim) {
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "name_248679064030400");
		t.dimensoes = dim;
		panel.setLayout(new GridLayout(0, t.dimensoes, 0, 0));
		t.tabuleirar();

		String[][] viewboard = new String[t.dimensoes][t.dimensoes];
		for (int linhas = 0; linhas < t.tabuleiro.length; linhas++) {
			for (int colunas = 0; colunas < t.tabuleiro.length; colunas++) {
				System.out.print(t.tabuleiro[linhas][colunas]);
				viewboard[linhas][colunas] = "*";
			}
			System.out.println();
		}

		JButton[][] botoes = new JButton[t.dimensoes][t.dimensoes];

		for (int i = 0; i < t.dimensoes; i++) {
			for (int j = 0; j < t.dimensoes; j++) {
				JButton btn = new JButton("");
				btn.setBackground(new Color(0, 128, 0));
				btn.setBounds(j, j, i, i);
				int x = i;
				int y = j;

				btn.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						try {
							if (t.tabuleiro[x][y] == 9) {
								botoes[x][y].setBackground(Color.RED);
								for (int i = 0; i < t.dimensoes; i++) {
									for (int j = 0; j < t.dimensoes; j++) {
										botoes[i][j].setEnabled(false);
										if (t.tabuleiro[i][j] == 9) {
											botoes[i][j].setBackground(Color.RED);
											setImageIcon(botoes[i][j],
													"C:\\Users\\renan.hardt\\Desktop\\Workspace\\TrabPratico\\src\\minesweepergame\\bomb.png",
													50, 50);
										} else {
											botoes[i][j].setText(String.valueOf(t.tabuleiro[i][j]));
											botoes[i][j].setForeground(Color.BLACK);
										}
									}
								}
								JFrame gameOverFrame = new JFrame("Game Over");
								gameOverFrame.setSize(300, 150);
								gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								gameOverFrame.setLayout(new BorderLayout());

								JLabel gameOverLabel = new JLabel("Você perdeu!");
								gameOverLabel.setHorizontalAlignment(JLabel.CENTER);

								JButton closeButton = new JButton("Close");
								closeButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										System.exit(0);
									}
								});

								JPanel panel = new JPanel();
								panel.add(closeButton);

								gameOverFrame.add(gameOverLabel, BorderLayout.CENTER);
								gameOverFrame.add(panel, BorderLayout.SOUTH);

								gameOverFrame.setVisible(true);
							} else if (t.tabuleiro[x][y] != 0) {
								viewboard[x][y] = String.valueOf(t.tabuleiro[x][y]);
								botoes[x][y].setText(String.valueOf(t.tabuleiro[x][y]));
								botoes[x][y].setForeground(Color.BLACK);
								botoes[x][y].setBackground(Color.yellow);
								botoes[x][y].setEnabled(false);
							} else {
								List<String> checklist = new ArrayList<>();
								List<String> checked = new ArrayList<>();

								for (int i = -1; i < 2; i++) {
									for (int j = -1; j < 2; j++) {
										try {
											viewboard[x + i][y + j] = String.valueOf(t.tabuleiro[x + i][y + j]);
											if (t.tabuleiro[x + i][y + j] == 0) {
												botoes[x + i][y + j].setText("");
											} else {
												botoes[x + i][y + j].setText(String.valueOf(t.tabuleiro[x + i][y + j]));
												botoes[x + i][y + j].setForeground(Color.BLACK);
											}
											if (t.tabuleiro[x + i][y + j] == 0) {
												checklist.add((String.valueOf((x + i) + " " + (y + j))));
											}
										} catch (Exception e1) {

										}
									}
								}
								checked.add(String.valueOf(x + " " + y));

								while (checklist.size() > 0) {
									String[] coordenadas = checklist.get(0).split(" ");
									int X = Integer.parseInt(coordenadas[0]);
									int Y = Integer.parseInt(coordenadas[1]);
									for (int i = -1; i < 2; i++) {
										for (int j = -1; j < 2; j++) {
											try {
												viewboard[X + i][Y + j] = String.valueOf(t.tabuleiro[X + i][Y + j]);
												if (t.tabuleiro[X + i][Y + j] == 0) {
													botoes[X + i][Y + j].setText("");
												} else {
													botoes[X + i][Y + j]
															.setText(String.valueOf(t.tabuleiro[X + i][Y + j]));
													botoes[X + i][Y + j].setForeground(Color.BLACK);
												}
												botoes[X + i][Y + j].setForeground(Color.BLACK);
												botoes[X + i][Y + j].setBackground(Color.yellow);
												if (t.tabuleiro[X + i][Y + j] == 0
														&& !checked.contains(checklist.get(0))) {
													checklist.add(String.valueOf((X + i) + " " + (Y + j)));
												}
											} catch (Exception e1) {

											}
										}
									}
									checked.add(checklist.get(0));
									checklist.remove(0);
								}
								for (int i = 0; i < t.dimensoes; i++) {
									for (int j = 0; j < t.dimensoes; j++) {
										if (viewboard[i][j] != "*") {
											botoes[i][j].setEnabled(false);
										}
									}
								}
							}
							int camposa = 0;
							for (int linhas = 0; linhas < t.tabuleiro.length; linhas++) {
								for (int colunas = 0; colunas < t.tabuleiro.length; colunas++) {
									if (viewboard[linhas][colunas] == "*") {
										camposa++;
									}
								}
							}
							if (camposa <= Math.pow(t.dimensoes, 2) - t.campos) {
								throw new Exception("Erro");
							}
						} catch (Exception e2) {
							JFrame gameOverFrame = new JFrame("Game Won");
							gameOverFrame.setSize(300, 150);
							gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							gameOverFrame.setLayout(new BorderLayout());

							JLabel gameOverLabel = new JLabel("Você ganhou!");
							gameOverLabel.setHorizontalAlignment(JLabel.CENTER);

							JButton closeButton = new JButton("Close");
							closeButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									System.exit(0); // You can customize this action
								}
							});

							JPanel panel = new JPanel();
							panel.add(closeButton);

							gameOverFrame.add(gameOverLabel, BorderLayout.CENTER);
							gameOverFrame.add(panel, BorderLayout.SOUTH);

							gameOverFrame.setVisible(true);
						}
					}
				});
				
				botoes[i][j] = btn;
				panel.add(btn);
			}
		}
		return panel;
	}

	private static void setImageIcon(JButton button, String imagePath, int width, int height) {
		ImageIcon icon = new ImageIcon(imagePath);
		Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(image);
		button.setIcon(scaledIcon);
	}
}
