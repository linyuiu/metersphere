package io.metersphere.plan.domain;

import io.metersphere.validation.groups.Created;
import io.metersphere.validation.groups.Updated;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

@Data
public class TestPlanCollection implements Serializable {
    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "{test_plan_collection.id.not_blank}", groups = {Updated.class})
    @Size(min = 1, max = 50, message = "{test_plan_collection.id.length_range}", groups = {Created.class, Updated.class})
    private String id;

    @Schema(description = "测试计划ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "{test_plan_collection.test_plan_id.not_blank}", groups = {Created.class})
    @Size(min = 1, max = 50, message = "{test_plan_collection.test_plan_id.length_range}", groups = {Created.class, Updated.class})
    private String testPlanId;

    @Schema(description = "所属测试集类型ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "{test_plan_collection.test_collection_type_id.not_blank}", groups = {Created.class})
    @Size(min = 1, max = 50, message = "{test_plan_collection.test_collection_type_id.length_range}", groups = {Created.class, Updated.class})
    private String testCollectionTypeId;

    @Schema(description = "测试集名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "{test_plan_collection.name.not_blank}", groups = {Created.class})
    @Size(min = 1, max = 255, message = "{test_plan_collection.name.length_range}", groups = {Created.class, Updated.class})
    private String name;

    @Schema(description = "执行方式(串行/并行)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "{test_plan_collection.execute_method.not_blank}", groups = {Created.class})
    @Size(min = 1, max = 50, message = "{test_plan_collection.execute_method.length_range}", groups = {Created.class, Updated.class})
    private String executeMethod;

    @Schema(description = "是否使用环境组", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "{test_plan_collection.grouped.not_blank}", groups = {Created.class})
    private Boolean grouped;

    @Schema(description = "环境ID/环境组ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "{test_plan_collection.environment_id.not_blank}", groups = {Created.class})
    @Size(min = 1, max = 50, message = "{test_plan_collection.environment_id.length_range}", groups = {Created.class, Updated.class})
    private String environmentId;

    @Schema(description = "自定义排序，间隔为2的N次幂", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "{test_plan_collection.pos.not_blank}", groups = {Created.class})
    private Long pos;

    @Schema(description = "创建人")
    private String createUser;

    @Schema(description = "创建时间")
    private Long createTime;

    private static final long serialVersionUID = 1L;

    public enum Column {
        id("id", "id", "VARCHAR", false),
        testPlanId("test_plan_id", "testPlanId", "VARCHAR", false),
        testCollectionTypeId("test_collection_type_id", "testCollectionTypeId", "VARCHAR", false),
        name("name", "name", "VARCHAR", true),
        executeMethod("execute_method", "executeMethod", "VARCHAR", false),
        grouped("grouped", "grouped", "BIT", false),
        environmentId("environment_id", "environmentId", "VARCHAR", false),
        pos("pos", "pos", "BIGINT", false),
        createUser("create_user", "createUser", "VARCHAR", false),
        createTime("create_time", "createTime", "BIGINT", false);

        private static final String BEGINNING_DELIMITER = "`";

        private static final String ENDING_DELIMITER = "`";

        private final String column;

        private final boolean isColumnNameDelimited;

        private final String javaProperty;

        private final String jdbcType;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        public String getJavaProperty() {
            return this.javaProperty;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        public static Column[] all() {
            return Column.values();
        }

        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}