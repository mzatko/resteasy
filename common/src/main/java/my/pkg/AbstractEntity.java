package my.pkg;

@javax.persistence.MappedSuperclass
public abstract class AbstractEntity {
    @javax.persistence.Id
    @javax.persistence.Column(name = "id", columnDefinition = "NUMERIC", nullable = true)
    @javax.persistence.TableGenerator(name = "tablegenerator", pkColumnName = "seq_name", valueColumnName = "seq_nextvalue", table = "sys_sequences", allocationSize = 50, initialValue = 1000)
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.TABLE, generator = "tablegenerator")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
