package iface;

import java.util.ArrayList;
import java.util.Scanner;

public class IFace {
	
	public static int retornarIndexUsername(ArrayList<Usuario> usuarios, String usernameUsuario) {
		int index = -1;
		for(int i = 0; i < usuarios.size(); i++) {
			if(usernameUsuario.equals(usuarios.get(i).getUsername())) 
				index = i;	
		}
		
		return index;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		int flag = 1;
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		ArrayList<Comunidade> comunidades = new ArrayList<Comunidade>();
		ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();
		
		
		Usuario nome1 = new Usuario("Karen", "Gomes", "karengomes", "kngs");
		Usuario nome2 = new Usuario("Joao", "Silva", "joaozinho", "silva123");
		
		usuarios.add(nome1);
		usuarios.add(nome2);

		Comunidade comunidade1 = new Comunidade("Alunos do IC", nome1);
		comunidades.add(comunidade1);
		
		Mensagem mensagem1 = new Mensagem(nome1,nome2,"Top");
		mensagens.add(mensagem1);
		
		String username, senha;
		
		do {
			System.out.printf("\n ======== iFace ========\n\n");
			System.out.println("- Digite a opção desejada -");
			System.out.println("[1] Criar conta");
			System.out.println("[2] Entrar na sua conta");
			System.out.println("[0] Sair");
			
			int opcao = entrada.nextInt();
			flag = opcao;
			
			if (opcao == 1) {
					
					System.out.println("Criando Conta");
					entrada.nextLine();
					System.out.println("Digite o nome do usuario:");
					String nome = entrada.nextLine();
					System.out.println("Digite o sobrenome do usuario:");
					String sobrenome = entrada.nextLine();
					System.out.println("Digite o username do usuario:");
					username = entrada.nextLine();
					System.out.println("Digite a senha do usuario:");
					senha = entrada.nextLine();
					
					Usuario usuarioNovo = new Usuario(nome, sobrenome, username, senha);
					
					usuarios.add(usuarioNovo);
					System.out.printf("Usuario criado com sucesso!\n\nSeja bem-vindo %s!", nome);

			} else if(opcao == 2) {
			
				//case 2:
					System.out.print("Digite o seu username: ");
					username = entrada.next().toUpperCase();
					int index = retornarIndexUsername(usuarios,username);
					
					if(index == -1) 
						System.out.println("Este username nao se refere a algum usuario do sistema");
					else {
						Usuario usuario = usuarios.get(index);
						//boolean confirmaSenha = usuario.confirmarSenha();
						//System.out.print("Digite a sua senha: ");
						//senha = entrada.next();
						if (!usuario.confirmarSenha())
							System.out.println("Senha invalida!");
						else {
							int voltar = 1;
							System.out.printf("Bem-vindo ao iFace, %s %s!\n", usuarios.get(index).getNome(), usuarios.get(index).getSobrenome());
							
							do {
								System.out.println("O que deseja fazer agora?");
								System.out.println("[1] Ver perfil"); //editar perfil e recuperar informacoes daquele usuario
								System.out.println("[2] Adicionar amigo");
								System.out.println("[3] Enviar mensagem a um amigo");
								System.out.println("[4] Criar comunidade");
								System.out.println("[5] Adicionar membro a uma comunidade");
								System.out.println("[6] Remover conta");
								System.out.println("[0] Sair do iFace");
								
								opcao = entrada.nextInt();
								voltar = opcao;
								
								switch(opcao) {
									case 1:
										boolean mostrarMensagens = usuario.mostrarConta();
										if(mostrarMensagens)
											usuario.mostrarMensagens(mensagens);
										System.out.println("Deseja editar algo em sua conta? [Y/N]");
										String editar = entrada.next().toUpperCase();
										if(editar.equals("Y")) {
											usuario.editarConta();
											System.out.println("Alteracao feita com sucesso!");
										}
										break;
									case 2:
										System.out.print("Digite o username do seu possivel amigo: ");
										String usernameAmigo = entrada.next().toUpperCase();
										int indexAmigo = retornarIndexUsername(usuarios,usernameAmigo);

										if(indexAmigo == -1) {
											System.out.println("Este username nao se refere a algum usuario do sistema");
										} else {
											Usuario amigo = usuarios.get(indexAmigo);
											Solicitacao solicitacaoNova = new Solicitacao(usuario,amigo);
											usuario.adicionarSolicitacaoAmizade(solicitacaoNova);
										}
												
										
										break;
									case 3:
										System.out.print("Digite o username do destinatario: ");
										String usernameDestinatario = entrada.next().toUpperCase();
										int indexDestinatario = retornarIndexUsername(usuarios,usernameDestinatario);
										
										if(indexDestinatario == -1) 
											System.out.println("Username nao se refere a algum usuario do sistema");
										else {
											Usuario destinatario = usuarios.get(indexDestinatario);
											entrada.nextLine();
											System.out.println("Digite a mensagem: ");
											String mensagemNova = entrada.nextLine();
											Mensagem addMensagem = new Mensagem(usuario,destinatario, mensagemNova);
											mensagens.add(addMensagem);
										}
										
										System.out.println();
										break;
									case 4:
										System.out.printf("Criando comunidade\n\n");
										entrada.nextLine();
										System.out.print("Nome: ");
										String nomeComunidade = entrada.nextLine();
										System.out.print("Descricao da Comunidade: ");
										String descricaoComunidade = entrada.nextLine();
										
										Comunidade novaComunidade = new Comunidade(nomeComunidade,descricaoComunidade, usuario);
										comunidades.add(novaComunidade);
										usuario.adicionarComunidadeUsuario(novaComunidade);
										break;
									case 5:
										System.out.printf("\nAdicionando membro a uma comunidade\n\n");
										entrada.nextLine();
										int indexComunidade = -1;
										System.out.print("Digite o nome da comunidade: ");
										nomeComunidade = entrada.nextLine().toUpperCase();
										for(int i = 0; i < comunidades.size(); i++) {
											if(comunidades.get(i).getNome().equals(nomeComunidade))
												indexComunidade = i;
										}
										
										if(indexComunidade == -1)
											System.out.println("Este nome nao se refere a alguma comunidade do sistema");
										else if (!comunidades.get(indexComunidade).getResponsavel().equals(usuario)) {
											System.out.printf("Voce nao e o Responsavel por esta comunidade.\n"
													+ "Apenas o responsavel por adicionar membros\n");
										}
										else {
											System.out.print("Digite o username do membro: ");
											String usernameMembro = entrada.next().toUpperCase();
											int indexMembro = retornarIndexUsername(usuarios,usernameMembro);

											if(indexMembro == -1) 
												System.out.println("Este username nao se refere a algum usuario do sistema ");
											else {
												Usuario membro = usuarios.get(indexMembro);
												comunidades.get(indexComunidade).adicionarMembro(usuario, membro);
											}
											
										}
										break;
									case 6:
										if(!usuario.confirmarSenha()) {
											System.out.println("Senha invalida!");
										} else {
											System.out.println("Voce tem certeza que deseja excluir sua conta? [Y/N]");
											String exclusao = entrada.next().toUpperCase();
											if(exclusao.equals("Y")) {
												usuarios.remove(index);
												System.out.println("Usuario excluido!");
											}
										}
										break;
									case 0: default:
										 break;
									
								}
								
								
							} while(voltar != 0);
	
						}
					}
			}

		} while(flag != 0);

		
		entrada.close();
	}

}