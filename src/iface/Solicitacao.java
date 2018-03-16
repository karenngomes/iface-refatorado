package iface;

public class Solicitacao {
	private static int idTotal;
	private int id;
	private Usuario usuarioRemetente;
	private Usuario usuarioDestinatario;
	
	
	public Solicitacao(Usuario usuarioRemetente, Usuario usuarioDestinatario) {
		super();
		this.id = ++idTotal;
		this.usuarioRemetente = usuarioRemetente;
		this.usuarioDestinatario = usuarioDestinatario;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario getUsuarioRemetente() {
		return usuarioRemetente;
	}
	public void setUsuarioRemetente(Usuario usuarioRemetente) {
		this.usuarioRemetente = usuarioRemetente;
	}
	public Usuario getUsuarioDestinatario() {
		return usuarioDestinatario;
	}
	public void setUsuarioDestinatario(Usuario usuarioDestinatario) {
		this.usuarioDestinatario = usuarioDestinatario;
	}
	
}
